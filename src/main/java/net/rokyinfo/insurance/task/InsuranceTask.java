package net.rokyinfo.insurance.task;

import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.enums.OrderStatus;
import net.rokyinfo.insurance.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * @author: yangyang.cao
 * @date: 2018-01-22 12:59
 **/
@Component
public class InsuranceTask {

    @Autowired
    private OrderService orderService;

    private static Logger logger = LoggerFactory.getLogger(InsuranceTask.class);

    /**
     * 每隔1小时执行一次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void checkOrderIfExpire() {

        Map<String, Object> params = new HashMap<>(1);
        params.put("status", OrderStatus.IN_INSURANCE.getOrderStatusValue());
        List<OrderEntity> orderEntityList = orderService.queryList(params);

        OrderEntity updateOrderEntity = new OrderEntity();

        for(OrderEntity orderEntity : orderEntityList) {

            Date expirationTime = orderEntity.getExpirationTime();
            if (expirationTime == null) {
                logger.error("非法订单：该订单没有过期时间。订单ID：" + orderEntity.getId());
                continue;
            }

            Date currentDate = new Date();
            //过期时间小于等于当前时间则过期
            if (!expirationTime.after(currentDate)) {
                updateOrderEntity.setId(orderEntity.getId());
                updateOrderEntity.setStatus(OrderStatus.EXPIRE.getOrderStatusValue());
                orderService.update(updateOrderEntity);
            }

        }

    }

}
