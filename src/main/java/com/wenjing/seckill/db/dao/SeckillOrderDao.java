package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillOrder;

public interface SeckillOrderDao {
    void insertOrder(SeckillOrder order);

    SeckillOrder queryOrder(String orderNo);

    void updateOrder(SeckillOrder order);

    SeckillOrder getOrderById(Long id);
}
