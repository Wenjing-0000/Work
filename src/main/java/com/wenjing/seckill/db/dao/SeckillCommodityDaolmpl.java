package com.wenjing.seckill.db.dao;


import com.wenjing.seckill.db.po.SeckillCommodity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SeckillCommodityDaolmpl implements SeckillCommodityDao {
    @Resource
    private SeckillCommodityMapper seckillCommodityMapper;

    @Override
    public SeckillCommodity querySeckillCommodityById(long commodityId) {
        return seckillCommodityMapper.getCommodityById(commodityId);
    }
}
