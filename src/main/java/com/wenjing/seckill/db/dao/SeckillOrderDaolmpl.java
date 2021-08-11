package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillOrder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SeckillOrderDaolmpl implements SeckillOrderDao {
    @Resource
    private SeckillOrderMapper seckillorderMapper;

    @Override
    public void insertOrder(SeckillOrder order) {
        seckillorderMapper.addOrder(order);
    }

    @Override
    public SeckillOrder queryOrder(String orderNo) {
        return seckillorderMapper.getOrderByNo(orderNo);
    }

    @Override
    public void updateOrder(SeckillOrder order) {
        seckillorderMapper.updateOrder(order);
    }

    @Override
    public SeckillOrder getOrderById(Long id) {
        return seckillorderMapper.getOrderById(id);
    }
}
