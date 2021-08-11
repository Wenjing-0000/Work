package com.wenjing.seckill.mq;


import com.alibaba.fastjson.JSON;
import com.wenjing.seckill.db.dao.SeckillActivityDao;
import com.wenjing.seckill.db.dao.SeckillOrderDao;
import com.wenjing.seckill.db.po.SeckillOrder;
import com.wenjing.seckill.services.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
@RocketMQMessageListener(topic = "seckill_order", consumerGroup = "seckill")
public class OrderConsumer implements RocketMQListener<MessageExt>{
    @Autowired
    private SeckillOrderDao orderDao;
    @Autowired
    private SeckillActivityDao seckillActivityDao;

    @Override
    @Transactional
    public void onMessage(MessageExt messageExt) {
        //1.解析创建订单请求消息
        String message = new String(messageExt.getBody(),
                StandardCharsets.UTF_8);
        log.info("接收到创建订单请求：" + message);
        SeckillOrder order = JSON.parseObject(message, SeckillOrder.class);
        order.setCreateTime(new Date());
        //2.扣减库存
        boolean lockStockResult =
                seckillActivityDao.deductStock(order.getSeckillActivityId());
        if (lockStockResult) {
            //订单状态 0:没有可⽤库存，⽆效订单 1:已创建等待付款
            order.setStatus(1);
        } else {
            order.setStatus(0);
        }
        //3.插⼊订单
        orderDao.insertOrder(order);
    }
}
