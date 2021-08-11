package com.wenjing.seckill.mq;


import com.alibaba.fastjson.JSON;
import com.wenjing.seckill.db.dao.SeckillActivityDao;
import com.wenjing.seckill.db.dao.SeckillOrderDao;
import com.wenjing.seckill.db.po.SeckillOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RocketMQMessageListener(topic = "pay_check", consumerGroup = "pay_check_group")
public class PayStatusCheckListener implements RocketMQListener<MessageExt> {
    @Autowired
    private SeckillOrderDao orderDao;

    @Autowired private SeckillActivityDao seckillActivityDao;

    @Override
    public void onMessage(MessageExt messageExt) {
        String message = new String(messageExt.getBody(),
                StandardCharsets.UTF_8);
        log.info("接收到订单⽀付状态校验消息:" + message);
        SeckillOrder order = JSON.parseObject(message, SeckillOrder.class);
        //1.查询订单
        SeckillOrder orderInfo = orderDao.queryOrder(String.valueOf(order.getCommodityId()));
        //2.判读订单是否完成⽀付
        if (orderInfo.getStatus() != 2) {
        //3.未完成⽀付关闭订单
            log.info("未完成⽀付关闭订单,订单号："+ order.getCommodityId());
            orderInfo.setStatus(99);
            orderDao.updateOrder(orderInfo);
            //4.回滚冻结的库存
            seckillActivityDao.revertStock(order.getSeckillActivityId());
        }
    }
}
