package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SeckillOrderMapper {
    public SeckillOrder getOrderById(Long id);

    public void addOrder(SeckillOrder order);

    public void updateOrder(SeckillOrder order);

    public void deleteOrder(Long id);

    public SeckillOrder getOrderByNo(String orderNo);

}
