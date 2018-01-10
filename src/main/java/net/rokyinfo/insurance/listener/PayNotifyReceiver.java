package net.rokyinfo.insurance.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.rokyinfo.insurance.entity.AlarmMsg;
import net.rokyinfo.insurance.entity.PayOrderNotifyMsg;
import net.rokyinfo.insurance.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yuanzhijian
 */
@Component
@RabbitListener(queues = "insurance.pay.notify.queue")
public class PayNotifyReceiver {

    private static Logger logger = LoggerFactory.getLogger(PayNotifyReceiver.class);

    @RabbitHandler
    public void process(String json) {

        PayOrderNotifyMsg payOrderNotifyMsg = JacksonUtil.readValue(json,PayOrderNotifyMsg.class);

        if (payOrderNotifyMsg != null) {
            // 存储Message
            logger.info(payOrderNotifyMsg.toString());
        }
    }


}
