package com.wenjing.seckill;

import com.wenjing.seckill.mq.RocketMQService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MQTest {
    @Autowired
    private RocketMQService rocketMQService;

    @Test
    public void sendMQTest() throws Exception {
        rocketMQService.sendMessage("test-wenjing","hello world"+ new Date().toString());
    }
}
