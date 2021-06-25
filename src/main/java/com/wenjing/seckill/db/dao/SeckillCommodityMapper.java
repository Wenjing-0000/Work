package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillCommodity;

public interface SeckillCommodityMapper {

    public SeckillCommodity getCommodityById(Integer id);

    public void addCommodity(SeckillCommodity commodity);

    public void updateCommodity(SeckillCommodity commodity);

    public void deleteCommodity(Long id);
}
