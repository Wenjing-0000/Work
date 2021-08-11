package com.wenjing.seckill;

import com.wenjing.seckill.services.RedisService;
import com.wenjing.seckill.services.SeckillActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {
    @Resource
    RedisService redisService;

    @Resource
    SeckillActivityService seckillActivityService;

    @Test
    public void setValueTest() {
        redisService.setValue("stock", 1L);
    }

    @Test
    public void getValueTest() {
        String stock = redisService.getValue("stock");
        System.out.println(stock);
    }

    @Test
    public void stockValidtorTest(){
        String key = "stock:658689";
        redisService.setValue(key, 10L);
        redisService.stockDeductValidator(key);
    }

    @Test
    public void pushSeckillInfoToRedisTest(){
        seckillActivityService.pushSeckillInfoToRedis(19);
    }
}
