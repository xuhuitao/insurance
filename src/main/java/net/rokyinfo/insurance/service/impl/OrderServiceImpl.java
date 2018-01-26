package net.rokyinfo.insurance.service.impl;

import net.rokyinfo.insurance.entity.*;
import net.rokyinfo.insurance.enums.*;
import net.rokyinfo.insurance.dao.OrderDao;
import net.rokyinfo.insurance.exception.RkException;
import net.rokyinfo.insurance.exception.RkInvalidRequestException;
import net.rokyinfo.insurance.retrofit.RemoteService;
import net.rokyinfo.insurance.service.OrderService;
import net.rokyinfo.insurance.service.ProductService;
import net.rokyinfo.insurance.service.SolutionService;
import net.rokyinfo.insurance.util.DateUtils;
import net.rokyinfo.insurance.util.R;
import net.rokyinfo.insurance.util.Sequence;
import org.apache.http.util.TextUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.ParseException;
import java.util.*;

@Service("insOrderService")
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Value("${insurance.res.image.storage.path.bill}")
    private String billImgPath;

    @Value("${insurance.res.image.storage.path.scooter}")
    private String scooterImgPath;

    @Value("${insurance.res.excel.storage.path}")
    private String excelPath;

    @Value("${insurance.res.excel.request}")
    private String excelUrl;

    @Value("${order.generative.day.limit}")
    private long dayLimit;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RemoteService remoteService;

    private final Sequence payOrderNoSequence = new Sequence(0, 0);

    private final Sequence excelSequence = new Sequence(0, 0);

    @Override
    public void affirm(Long orderId, Integer dispose) throws IOException {

        OrderEntity orderEntity = orderDao.queryObject(orderId);
        if (orderEntity == null) {
            throw new RkException("不存在该订单");
        }
        if (orderEntity.getStatus() != OrderStatus.PAYED_TO_VERIFY.getOrderStatusValue()) {
            throw new RkException("不是支付待审核的订单");
        }

        if (dispose == OrderDispose.REFUSE.getOrderDisposeValue()) {

            OrderEntity updateOrderEntity = new OrderEntity();
            updateOrderEntity.setId(orderEntity.getId());
            updateOrderEntity.setStatus(OrderStatus.REFUSE_AND_UNREFUND.getOrderStatusValue());
            updateOrderEntity.setModifier(CreatorEnum.SYSTEM.getCreator());
            updateOrderEntity.setModifyTime(new Date());
            orderDao.update(updateOrderEntity);

            remoteService.refundByOrderNo(orderEntity.getOrderNo());

        } else if (dispose == OrderDispose.PASS.getOrderDisposeValue()) {

            OrderEntity updateOrderEntity = new OrderEntity();
            updateOrderEntity.setId(orderEntity.getId());
            updateOrderEntity.setStatus(OrderStatus.IN_INSURANCE.getOrderStatusValue());
            updateOrderEntity.setActivationTime(new Date());
            SolutionEntity solutionEntity = orderEntity.getSolutionEntity();
            if (solutionEntity == null) {
                throw new RkException("订单异常，该订单不存在保险产品方案");
            }
            Date expirationTime = DateUtils.addMonth(updateOrderEntity.getActivationTime(), solutionEntity.getIndate());
            updateOrderEntity.setExpirationTime(expirationTime);
            updateOrderEntity.setModifier(CreatorEnum.SYSTEM.getCreator());
            updateOrderEntity.setModifyTime(new Date());
            orderDao.update(updateOrderEntity);

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
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }

    @Override
    public List<OrderEntity> queryListByStatusArray(Map<String, Object> map) {
        return orderDao.queryListByStatusArray(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public int queryTotalByStatusArray(Map<String, Object> map) {
        return orderDao.queryTotalByStatusArray(map);
    }

    @Override
    public void save(OrderEntity insOrder) {
        orderDao.save(insOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R save(OrderEntity insOrder, MultipartFile billFile, MultipartFile[] scooterFiles) throws IOException, ParseException {

        Map<String, Object> params = new HashMap<>();
        params.put("ccuSn", insOrder.getCcuSn());
        String[] statusArray = new String[] { String.valueOf(OrderStatus.PAYED_TO_VERIFY.getOrderStatusValue()),
                String.valueOf(OrderStatus.IN_INSURANCE.getOrderStatusValue())};
        params.put("status", statusArray);
        List<OrderEntity> orderEntityList = orderDao.queryListByStatusArray(params);
        if (orderEntityList != null && orderEntityList.size() > 0) {
            throw new RkException("该中控序列号已存在订单");
        }

        List<String> ccuSnList = new ArrayList<>();
        ccuSnList.add(insOrder.getCcuSn());
        List<Ebike> ebikeList = remoteService.getEbikeList(ccuSnList);
        if (ebikeList == null || ebikeList.size() == 0) {
            throw new RkException("不存在该中控序列号，请核对中控序列号");
        }

        Ebike ebike = ebikeList.get(0);

        if (!ebike.isOnline()) {
            throw new RkException("该车辆已离线，不能生成订单");
        }

        String simValidityTimeStr = ebike.getSimValidityTime();
        if (isOutOfTimeLimit(simValidityTimeStr)) {
            throw new RkException("设备SIM卡有效期不在可保险时间内");
        }

        boolean existInsurance = false;
        if (!TextUtils.isEmpty(ebike.getInsuranceId())) {
            //该设备已经存在保险,重置solutionId
            existInsurance = true;
            insOrder.setSolutionId(Long.valueOf(ebike.getInsuranceId()));
            insOrder.setStatus(OrderStatus.PAYED_TO_VERIFY.getOrderStatusValue());
        } else {
            insOrder.setStatus(OrderStatus.TO_PAY.getOrderStatusValue());
        }

        SolutionEntity solutionEntity = solutionService.queryObject(insOrder.getSolutionId());
        if (solutionEntity == null) {
            throw new RkException("不存在该保险产品方案");
        }
        insOrder.setPrice(solutionEntity.getPrice());

        String orderNo = String.valueOf(payOrderNoSequence.nextId());

        String payOrderString = null;
        if (!existInsurance) {
            //不存在预置保险的，生成支付宝支付订单
            ChargeProductEntity chargeProductEntity = new ChargeProductEntity();
            chargeProductEntity.setCode(solutionEntity.getCode());
            chargeProductEntity.setDesc(solutionEntity.getDesc());
            chargeProductEntity.setName(solutionEntity.getName());
            chargeProductEntity.setPrice(insOrder.getPrice().doubleValue());

            payOrderString = remoteService.createPayOrder(insOrder.getUserId(), PayChannel.ALI_PAY.getPayChannelId(),
                    PayType.WAP.getPayType(), insOrder.getPrice().doubleValue(), orderNo, chargeProductEntity);

            if (TextUtils.isEmpty(payOrderString)) {
                throw new RkException("支付订单生成异常");
            }
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
        insOrder.setCreator(CreatorEnum.SYSTEM.getCreator());
        insOrder.setOrderNo(orderNo);
        insOrder.setCreateTime(curTime);
        orderDao.save(insOrder);

        return new R<>(payOrderString);

    }

    @Override
    public void update(String ccuSn, String orderNo) throws IOException, ParseException {

        if (TextUtils.isEmpty(ccuSn)) {
            throw new RkException("请指定设备序列号");
        }
        if (TextUtils.isEmpty(orderNo)) {
            throw new RkException("请指定订单号");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("orderNo", orderNo);
        params.put("status", OrderStatus.IN_INSURANCE.getOrderStatusValue());
        List<OrderEntity> orderEntityList = orderDao.queryList(params);
        if (orderEntityList == null || orderEntityList.size() == 0) {
            throw new RkException("只有保障中的订单才能进行设备更换");
        }

        List<String> ccuSnList = new ArrayList<>();
        ccuSnList.add(ccuSn);
        List<Ebike> ebikeList = remoteService.getEbikeList(ccuSnList);
        if (ebikeList == null || ebikeList.size() == 0) {
            throw new RkException("不存在该中控序列号，请核对中控序列号");
        }

        Ebike ebike = ebikeList.get(0);

        if (!ebike.isOnline()) {
            throw new RkException("设备已离线，不能变更为该设备");
        }

        String simValidityTimeStr = ebike.getSimValidityTime();
        if (isOutOfTimeLimit(simValidityTimeStr)) {
            throw new RkException("设备SIM卡有效期不在可保险时间内");
        }

        OrderEntity orderEntity = orderEntityList.get(0);
        OrderEntity updateOrderEntity = new OrderEntity();
        updateOrderEntity.setId(orderEntity.getId());
        updateOrderEntity.setCcuSn(ccuSn);
        orderDao.update(updateOrderEntity);
    }

    private boolean isOutOfTimeLimit(String simValidityTimeStr) throws ParseException {
        Date simValidityTime = DateUtils.parse(simValidityTimeStr, DateUtils.DATE_PATTERN);
        Date currentDate = new Date();
        long timeInterval = simValidityTime.getTime() - currentDate.getTime();
        boolean isExpire = (timeInterval < 0);
        long dayLimitMillisecond = (dayLimit * 24 * 60 * 60 * 1000);
        boolean outOfTimeLimit = (timeInterval > 0 && timeInterval <= dayLimitMillisecond);
        if (isExpire || outOfTimeLimit) {
            return true;
        }
        return false;
    }

    @Override
    public R payInfo(String orderNo) throws IOException {

        if (TextUtils.isEmpty(orderNo)) {
            throw new RkException("请指定订单号");
        }

        OrderEntity orderEntity = orderDao.queryOrderByOrderNo(orderNo);
        if (orderEntity == null) {
            throw new RkException("不存在该订单");
        }

        if (orderEntity.getStatus() != OrderStatus.TO_PAY.getOrderStatusValue()) {
            throw new RkException("该订单不是待支付状态");
        }

        SolutionEntity solutionEntity = solutionService.queryObject(orderEntity.getSolutionId());
        if (solutionEntity == null) {
            throw new RkException("不存在该保险产品方案");
        }

        ChargeProductEntity chargeProductEntity = new ChargeProductEntity();
        chargeProductEntity.setCode(solutionEntity.getCode());
        chargeProductEntity.setDesc(solutionEntity.getDesc());
        chargeProductEntity.setName(solutionEntity.getName());
        chargeProductEntity.setPrice(orderEntity.getPrice().doubleValue());

        String payOrderString = remoteService.createPayInfo(PayChannel.ALI_PAY.getPayChannelId(), PayType.WAP.getPayType(), orderNo, chargeProductEntity);

        if (TextUtils.isEmpty(payOrderString)) {
            throw new RkException("支付订单生成异常");
        }

        return new R<>(payOrderString);
    }

    @Override
    public void update(OrderEntity insOrder) {
        orderDao.update(insOrder);
    }

    @Override
    public String generateExcel(Map<String, Object> map) {

        List<OrderEntity> orderEntityList = orderDao.queryListByStatusArray(map);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), OrderEntity.class, orderEntityList);

        String fileName = "insurance-order-" + excelSequence.nextId() + ".xls";
        File file = new File(excelPath, fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return excelUrl + fileName;
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
