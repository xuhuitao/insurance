package net.rokyinfo.insurance.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.rokyinfo.insurance.entity.AlarmMsg;
import net.rokyinfo.insurance.entity.PayOrderNotifyMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlarmMsgReceiverTest {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void sendMsg(){

        AlarmMsg pushMsg = new AlarmMsg();
        pushMsg.setAlert("测试");
        pushMsg.setAlarmType("00");
        pushMsg.setCcSn("B000000000");
        pushMsg.setContent("测试消息~~~~~~~");
        pushMsg.setFirmFlag("rq");
        pushMsg.setUserId("165");

        ObjectMapper mapper = new ObjectMapper();
        String message = null;
        try {
            message = mapper.writeValueAsString(pushMsg);
        } catch (JsonProcessingException e) {
            return;
        }
        this.rabbitTemplate.convertAndSend("insurance.alarm.msg.queue", message);
    }

    @Test
    public void sendPayNotifyMsg(){

        PayOrderNotifyMsg pushMsg = new PayOrderNotifyMsg();
        pushMsg.setStatus("SUCCESS");
        pushMsg.setTrxNo("00000000000123123");
        pushMsg.setMerchantOrderNo("1111111111111111111");
        pushMsg.setOrderAmount(new BigDecimal(9.8) );
        ObjectMapper mapper = new ObjectMapper();
        String message = null;
        try {
            message = mapper.writeValueAsString(pushMsg);
        } catch (JsonProcessingException e) {
            return;
        }
        this.rabbitTemplate.convertAndSend("insurance.pay.notify.queue", message);
    }

}