package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.entity.ChargeProductEntity;
import net.rokyinfo.insurance.enums.OrderStatus;
import net.rokyinfo.insurance.dao.OrderDao;
import net.rokyinfo.insurance.dao.SolutionDao;
import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.entity.SolutionEntity;
import net.rokyinfo.insurance.exception.RkInvalidRequestException;
import net.rokyinfo.insurance.retrofit.CreatePayOrderWrapper;
import net.rokyinfo.insurance.service.OrderService;
import net.rokyinfo.insurance.util.R;
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

    @Value("${business.insurance.image.storage.address}")
    private String businessLicenseImgAddress;

    @Value("${business.insurance.image.request.url.prefix}")
    private String businessLicenseImgUrl;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SolutionDao solutionDao;

    @Autowired
    private CreatePayOrderWrapper createPayOrderWrapper;

    @Override
    public OrderEntity queryObject(Long id) {
        return orderDao.queryObject(id);
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
    public R save(OrderEntity insOrder, MultipartFile billFile, MultipartFile scooterFiles) {

        SolutionEntity solutionEntity = solutionDao.queryObject(insOrder.getSolutionId());
        insOrder.setPrice(solutionEntity.getPrice());

        ChargeProductEntity chargeProductEntity = new ChargeProductEntity();

        //TODO 异常处理
        String payOrder = "";
        try {
            payOrder = createPayOrderWrapper.createPayOrder(insOrder.getUserId(), 1L, "APP",
                    insOrder.getPrice().doubleValue(), insOrder.getOrderNo(), chargeProductEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String billFileLink = storeFile(billFile);
        insOrder.setBillImg(billFileLink);

        String scooterFileLink = storeFile(scooterFiles);
        insOrder.setScooterImg(scooterFileLink);

        Date curTime = new Date();
        insOrder.setVersion(0);
        insOrder.setBelong(0L);
        insOrder.setStatus(OrderStatus.TO_PAY.getOrderStatusValue());
        insOrder.setCreateTime(curTime);
        orderDao.save(insOrder);

        return new R<>(payOrder);

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
    private String storeFile(MultipartFile file) {
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

            File imageFile = new File(businessLicenseImgAddress, newFileName);
            // 检测是否存在目录
            if (!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(imageFile);

                return businessLicenseImgUrl + newFileName;

            } catch (IllegalStateException | IOException e) {
                logger.error("err:" + e);

            }
        }
        return "";
    }

}
