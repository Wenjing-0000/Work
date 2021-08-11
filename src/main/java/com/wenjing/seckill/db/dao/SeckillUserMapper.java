package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SeckillUserMapper {
    public SeckillUser getUserById(Long id);

    public void addUser(SeckillUser user);

    public void updateUser(SeckillUser user);

    public void deleteUser(Long id);
}
