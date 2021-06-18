package com.jiuzhang.seckill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.jiuzhang.seckill.SeckillActivity;

public class seckillActivityDao extends BaseDao<SeckillActivity>{

    public static void inertSeckillActivity(SeckillActivity seckillActivity){
        String sql="insert into seckillActivity(name,commodityId,seckillPrice,oldPrice,seckillNumber) values(?,?,?,?)";
        DbUtil dbUtil = new DbUtil() ;
        Connection connection = dbUtil.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seckillActivity.getName());
            preparedStatement.setLong(2, seckillActivity.getCommodityId());
            preparedStatement.setBigDecimal(3, seckillActivity.getOldPrice());
            preparedStatement.setBigDecimal(4, seckillActivity.getSeckillPrice());
            preparedStatement.setLong(5, seckillActivity.getSeckillNumber());
            preparedStatement.setInt(6, seckillActivity.getActivityStatus());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
