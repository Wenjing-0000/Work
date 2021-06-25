package com.wenjing.seckill.db.dao;

import com.wenjing.seckill.db.po.SeckillUser;

public interface SeckillUserMapper {
    public SeckillUser getUserById(Integer id);

    public void addUser(SeckillUser user);

    public void updateUser(SeckillUser user);

    public void deleteUser(Long id);
}
