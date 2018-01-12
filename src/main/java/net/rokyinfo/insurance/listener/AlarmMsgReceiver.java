package net.rokyinfo.insurance.listener;

import net.rokyinfo.insurance.entity.AlarmMessageEntity;
import net.rokyinfo.insurance.entity.AlarmMsg;
import net.rokyinfo.insurance.entity.OrderEntity;
import net.rokyinfo.insurance.enums.OrderStatus;
import net.rokyinfo.insurance.service.AlarmMessageService;
import net.rokyinfo.insurance.service.OrderService;
import net.rokyinfo.insurance.util.JacksonUtil;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * @author yuanzhijian
 */
@Component
@RabbitListener(queues = "insurance.alarm.msg.queue")
public class AlarmMsgReceiver {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AlarmMessageService alarmMessageService;

    private static Logger logger = LoggerFactory.getLogger(AlarmMsgReceiver.class);

    @RabbitHandler
    public void process(String json) {
        AlarmMsg alarmMsg = JacksonUtil.readValue(json,AlarmMsg.class);
        if (alarmMsg == null) {
            return;
        }
        // 存储Message
        logger.info(alarmMsg.toString());
        OrderEntity orderEntity = orderService.queryOrderByCcuSn(alarmMsg.getCcSn());
        if (orderEntity == null) {
            return;
        }
        if (orderEntity.getStatus() != OrderStatus.IN_INSURANCE.getOrderStatusValue()) {
            return;
        }
        AlarmMessageEntity alarmMessageEntity = new AlarmMessageEntity();
        alarmMessageEntity.setVersion(0);
        alarmMessageEntity.setCreator("system");
        alarmMessageEntity.setCcuSn(alarmMsg.getCcSn());
        if (!TextUtils.isEmpty(alarmMsg.getAlarmType())) {
            alarmMessageEntity.setAlarmType(Integer.parseInt(alarmMsg.getAlarmType()));
        }
        alarmMessageEntity.setContent(alarmMsg.getContent());
        alarmMessageEntity.setBelong(orderEntity.getBelong());
        Date curTime = new Date();
        alarmMessageEntity.setCreateTime(curTime);
        alarmMessageEntity.setAlarmTime(curTime);
        alarmMessageService.save(alarmMessageEntity);
    }

}
