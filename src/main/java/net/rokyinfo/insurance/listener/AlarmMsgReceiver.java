package net.rokyinfo.insurance.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.rokyinfo.insurance.entity.AlarmMsg;
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
@RabbitListener(queues = "insurance.alarm.msg.queue")
public class AlarmMsgReceiver {

    private static Logger logger = LoggerFactory.getLogger(AlarmMsgReceiver.class);

    @RabbitHandler
    public void process(String json) {
        AlarmMsg alarmMsg = JacksonUtil.readValue(json,AlarmMsg.class);

        if (alarmMsg != null){
            // 存储Message
            logger.info(alarmMsg.toString());
        }

    }


}
