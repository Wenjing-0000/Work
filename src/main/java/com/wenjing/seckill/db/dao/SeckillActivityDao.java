package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillActivity;

import java.util.List;

public interface SeckillActivityDao {
    public void insertSeckillActivity(SeckillActivity seckillActivity);

    public void updateSeckillActivity(SeckillActivity seckillActivity);

    public SeckillActivity querySeckillActivityById(long activityId);

    public List<SeckillActivity> querySeckillActivitysByStatus(int activityStatus);

    boolean lockStock(Long seckillActivityId);

    boolean deductStock(Long seckillActivityId);

    void revertStock(Long seckillActivityId);
}
