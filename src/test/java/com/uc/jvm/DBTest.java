package com.uc.jvm;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yangzhen on 17/8/28.
 */
public class DBTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
// 通过java库获取数据库连接
        Connection conn = java.sql.DriverManager.getConnection(url, "root", "123456");
        System.out.println(conn);
    }
}
