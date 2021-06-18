package com.jiuzhang.seckill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private String url = "jdbc:mysql://localhost:3306/seckill_jiuzhang?useUnicode=true&characterEncoding=UTF-8";
    private String dbUser = "root" ;
    private String dbPassword = "password" ;
    private String dbDriver = "com.mysql.jdbc.Driver" ;
    private Connection connection = null ;

    public Connection getConnection() {
        try {
            Class.forName(dbDriver) ;
            connection = DriverManager.getConnection(url,dbUser,dbPassword) ;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection ;
    }

    public void closeConnection() {
        if(connection !=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil() ;
        dbUtil.getConnection();
    }

}
