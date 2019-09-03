package com.ckong.schedule.utils;

import java.sql.*;

/**
 * 数据库连接工具类 MariaDB
 * @author kongzhiqiang
 * */
public class DbUtil {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "q7878478";
    private static final String URL = "jdbc:mariadb://120.79.226.237:3306/class_schedule_wechat";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public static Connection getConnection() {
        try {
           return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static  void close(Connection conn)  {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void close(Statement stat) {
        try {
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
