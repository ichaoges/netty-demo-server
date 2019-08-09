package main.model;

import java.sql.*;

public class Connect {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://47.98.142.108:23306/" + "ichaoge_netty" + "?useSSL=false";
    private static String user = "root";
    private static String password = "g7v5rI8PEqdfmX1rztDf";

    public static final Connection getConn() throws SQLException {
        System.out.println("开始连接数据库...");

        return  DriverManager.getConnection(url, user, password);
    }

    public static final void closeConn (Connection conn, Statement stamt, ResultSet rs) {
        System.out.println("关闭数据库连接！");

        try {
            if (conn != null) conn.close();
            if (stamt != null) stamt.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
