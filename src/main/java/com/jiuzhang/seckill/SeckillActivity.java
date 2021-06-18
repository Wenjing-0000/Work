package com.jiuzhang.seckill;

import java.math.BigDecimal;

public class SeckillActivity {

    String name;
    long commodityId;
    BigDecimal seckillPrice;
    BigDecimal oldPrice;
    long seckillNumber;
    int activityStatus;

    public SeckillActivity() {
        name = null;
        commodityId = 0;
        seckillPrice = null;
        oldPrice = null;
        seckillNumber = 0;
        activityStatus = 0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCommodityId(long commodityId){
        this.commodityId = commodityId;
    }

    public void setSeckillPrice(BigDecimal seckillPrice){
        this.seckillPrice = seckillPrice;
    }

    public void setOldPrice(BigDecimal oldPrice){
        this.oldPrice = oldPrice;
    }

    public void setSeckillNumber(long seckillNumber){
        this.seckillNumber = seckillNumber;
    }

    public void setActivityStatus(int activityStatus){
        this.activityStatus = activityStatus;
    }

    public String toString(){
        return "name = " + name + ", commodityId = " + commodityId + ", oldPrice = " + oldPrice + ", seckillPrice = "
                + seckillPrice + ", seckillNumber = " + seckillNumber + ", activityStatus = " + activityStatus;
    }

}
