package com.wenjing.seckill.db.po;

import java.math.BigDecimal;
import java.util.Date;

public class SeckillOrder {
    private Long id;

    private Long commodityId;

    private Long seckillActivityId;

    private BigDecimal money;

    private Long userId;

    private Integer status;

    private Date payTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getSeckillActivityId() {
        return seckillActivityId;
    }

    public void setSeckillActivityId(Long seckillActivityId) {
        this.seckillActivityId = seckillActivityId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
