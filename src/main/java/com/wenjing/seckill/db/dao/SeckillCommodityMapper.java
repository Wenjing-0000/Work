package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillCommodity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SeckillCommodityMapper {

    public SeckillCommodity getCommodityById(Long id);

    public void addCommodity(SeckillCommodity commodity);

    public void updateCommodity(SeckillCommodity commodity);

    public void deleteCommodity(Long id);
}
