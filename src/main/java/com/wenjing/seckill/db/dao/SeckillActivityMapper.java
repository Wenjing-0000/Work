package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillActivity;


import java.util.List;


public interface SeckillActivityMapper {
    public SeckillActivity getActivityById(Long id);

    public void addActivity(SeckillActivity activity);

    public void updateActivity(SeckillActivity activity);

    public void deleteActivity(Long id);

    int insertSelective(SeckillActivity record);

    List<SeckillActivity> querySeckillActivitysByStatus(int activityStatus);

    int lockStock(Long id);

    int deductStock(Long id);

    void revertStock(Long seckillActivityId);

}
