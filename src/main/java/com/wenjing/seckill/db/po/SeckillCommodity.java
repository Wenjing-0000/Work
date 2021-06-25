package com.wenjing.seckill.db.po;

import java.util.Date;

public class SeckillCommodity {
    private Long id;

    private String name;

    private String desc;

    private Integer price;

//    public SeckillCommodity(){
//        super();
//    }
//
//    public SeckillCommodity(Long id, String name, String desc, Integer price) {
//        this.id = id;
//        this.name = name;
//        this.desc = desc;
//        this.price = price;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
