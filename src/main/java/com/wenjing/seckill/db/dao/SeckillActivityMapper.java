package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillActivity;

public interface SeckillActivityMapper {
    public SeckillActivity getActivityById(Integer id);

    public void addActivity(SeckillActivity activity);

    public void updateActivity(SeckillActivity activity);

    public void deleteActivity(Long id);
}
