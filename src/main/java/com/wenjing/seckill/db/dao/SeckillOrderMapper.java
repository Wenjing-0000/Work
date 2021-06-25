package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillOrder;

public interface SeckillOrderMapper {
    public SeckillOrder getOrderById(Integer id);

    public void addOrder(SeckillOrder order);

    public void updateOrder(SeckillOrder order);

    public void deleteOrder(Long id);
}
