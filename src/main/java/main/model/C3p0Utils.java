package main.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Utils {
    private static ComboPooledDataSource ds = null;

    static{
        try{
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://47.98.142.108:23306/ichaoge_netty");
            ds.setUser("root");
            ds.setPassword("g7v5rI8PEqdfmX1rztDf");

            ds.setInitialPoolSize(10);//最初连接数
            ds.setMinPoolSize(5);//最小连接数
            ds.setMaxPoolSize(20);//最大连接数

//            ds = new ComboPooledDataSource("mysql");

        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConn() throws SQLException{

        return ds.getConnection();
    }

    public static void closeConn(Connection conn, Statement st,ResultSet rs){
        System.out.println("关闭数据库连接！");

        try {
            if (conn != null) conn.close();
            if (st != null) st.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
