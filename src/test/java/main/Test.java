package main;

import main.model.C3p0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        String sql = "SELECT id, name, password, last_login_at FROM user";
        String sql1 = "UPDATE `card` SET `amount`='444' WHERE (`id`='3')";
        String sql2 = "UPDATE `card` SET `amount`='444' WHERE (`id`='2') ";

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            c = C3p0Utils.getConn();
            c.setAutoCommit(false);

            st = c.prepareStatement(sql1);
            st.executeUpdate();


            st = c.prepareStatement(sql2);
            st.executeUpdate();

            c.commit();

            // 展开结果集数据库
//            while (rs.next()) {
//                // 通过字段检索
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String password = rs.getString("password");
//                Timestamp last_login_at = rs.getTimestamp("last_login_at");
//
//                // 输出数据
//                System.out.print("ID: " + id);
//                System.out.print(", 用户名: " + name);
//                System.out.print(", 密码: " + password);
//                System.out.print(", 日期: " + last_login_at);
//                System.out.print("\n");
//            }

        } finally {
            C3p0Utils.closeConn(c, st, rs);
        }
    }
}
