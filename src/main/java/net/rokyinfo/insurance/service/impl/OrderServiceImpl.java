package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import net.rokyinfo.insurance.entity.ProductEntity;
import net.rokyinfo.insurance.enums.OrderDispose;
import net.rokyinfo.insurance.enums.OrderStatus;
import net.rokyinfo.insurance.dao.OrderDao;
import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.entity.SolutionEntity;
import net.rokyinfo.insurance.enums.PayChannel;
import net.rokyinfo.insurance.enums.PayType;
import net.rokyinfo.insurance.exception.RkException;
import net.rokyinfo.insurance.exception.RkInvalidRequestException;
import net.rokyinfo.insurance.retrofit.RemoteService;
import net.rokyinfo.insurance.service.OrderService;
import net.rokyinfo.insurance.service.ProductService;
import net.rokyinfo.insurance.service.SolutionService;
import net.rokyinfo.insurance.util.R;
import net.rokyinfo.insurance.util.Sequence;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("insOrderService")
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Value("${insurance.res.image.storage.path.bill}")
    private String billImgPath;

    @Value("${insurance.res.image.storage.path.scooter}")
    private String scooterImgPath;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RemoteService remoteService;

    private final Sequence payOrderNoSequence = new Sequence(0, 0);

    @Override
    public void affirm(Long orderId, Integer dispose) {
        OrderEntity orderEntity = orderDao.queryObject(orderId);
        if (orderEntity == null) {
            throw new RkException("不存在该订单");
        }
        if (orderEntity.getStatus() != OrderStatus.PAYED_TO_VERIFY.getOrderStatusValue()) {
            throw new RkException("不是支付待审核的订单");
        }
        if (dispose == OrderDispose.REFUSE.getOrderDisposeValue()) {
            orderEntity.setStatus(OrderStatus.REFUSE_AND_UNREFUND.getOrderStatusValue());
            orderDao.update(orderEntity);
        } else if (dispose == OrderDispose.PASS.getOrderDisposeValue()) {
            orderEntity.setStatus(OrderStatus.IN_INSURANCE.getOrderStatusValue());
            orderDao.update(orderEntity);
        } else {
            throw new RkException("非法的订单操作，只能是通过或者拒绝操作");
        }
    }

    @Override
    public OrderEntity queryObject(Long id) {
        return orderDao.queryObject(id);
    }

    @Override
    public OrderEntity queryOrderByOrderNo(String orderNo) {
        return orderDao.queryOrderByOrderNo(orderNo);
    }

    @Override
    public List<String> queryCcuSnOfOrder(Map<String, Object> map) {
        return orderDao.queryCcuSnOfOrder(map);
    }

    @Override
    public OrderEntity queryOrderByCcuSn(String ccuSn) {
        return orderDao.queryOrderByCcuSn(ccuSn);
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public void save(OrderEntity insOrder) {
        orderDao.save(insOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R save(OrderEntity insOrder, MultipartFile billFile, MultipartFile[] scooterFiles) throws IOException {

        SolutionEntity solutionEntity = solutionService.queryObject(insOrder.getSolutionId());
        if (solutionEntity == null) {
            throw new RkException("不存在该保险产品方案");
        }
        insOrder.setPrice(solutionEntity.getPrice());

        ChargeProductEntity chargeProductEntity = new ChargeProductEntity();
        chargeProductEntity.setCode(solutionEntity.getCode());
        chargeProductEntity.setDesc(solutionEntity.getDesc());
        chargeProductEntity.setName(solutionEntity.getName());
        chargeProductEntity.setPrice(insOrder.getPrice().doubleValue());

        String orderNo = String.valueOf(payOrderNoSequence.nextId());

        String payOrderString = remoteService.createPayOrder(insOrder.getUserId(), PayChannel.ALI_PAY.getPayChannelId(),
                PayType.APP.getPayType(), insOrder.getPrice().doubleValue(), orderNo, chargeProductEntity);

        if (TextUtils.isEmpty(payOrderString)) {
            throw new RkException("支付订单生成异常");
        }

        String billFileLink = storeFile(billFile, billImgPath);
        insOrder.setBillImg(billFileLink);

        List<String> scooterImgLinks = new ArrayList<String>();
        Arrays.asList(scooterFiles).forEach(scooterFile -> {
            String scooterFileLink = storeFile(scooterFile, scooterImgPath);
            scooterImgLinks.add(scooterFileLink);
        });
        insOrder.setScooterImg(scooterImgLinks);

        ProductEntity productEntity = productService.queryObject(insOrder.getProductId());
        if (productEntity == null) {
            throw new RkException("不存在该保险产品");
        }
        insOrder.setBelong(productEntity.getBelong());

        Date curTime = new Date();
        insOrder.setVersion(0);
        insOrder.setStatus(OrderStatus.TO_PAY.getOrderStatusValue());
        insOrder.setOrderNo(orderNo);
        insOrder.setCreateTime(curTime);
        orderDao.save(insOrder);

        return new R<>(payOrderString);

    }

    @Override
    public void update(OrderEntity insOrder) {
        orderDao.update(insOrder);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        orderDao.deleteBatch(ids);
    }

    /**
     * 存储文件
     * @param file
     */
    private String storeFile(MultipartFile file, String storagePath) {
        if (file != null && !file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")
                    && !fileName.endsWith(".png") && !fileName.endsWith(".gif")) {
                throw new RkInvalidRequestException("上传图片文件格式错误", HttpStatus.BAD_REQUEST.value());
            }

            logger.info("上传图片的文件名为：" + fileName);


            String extName = fileName.substring(fileName.lastIndexOf("."));
            String fileNameWithoutSuffix = fileName.substring(0,
                    fileName.lastIndexOf("."));


            Random random = new Random();
            StringBuilder randomName = new StringBuilder();
            for (int i = 0; i < 4; i++) {

                randomName.append(String.valueOf(random.nextInt(10)));
            }

            String newFileName = fileNameWithoutSuffix
                    + "." + randomName.toString()
                    + extName;

            File imageFile = new File(storagePath, newFileName);
            // 检测是否存在目录
            if (!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(imageFile);

                return newFileName;

            } catch (IllegalStateException | IOException e) {
                logger.error("err:" + e);

            }
        }
        return null;
    }

}
