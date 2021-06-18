package com.jiuzhang.seckill;

import java.sql.Connection;

import com.jiuzhang.seckill.DbUtil;

public class BaseDao<T> {
    private DbUtil dbUtil = new DbUtil() ;
    public Connection connection = dbUtil.getConnection();
    public void closeConnection() {
        dbUtil.closeConnection();
    }
}
