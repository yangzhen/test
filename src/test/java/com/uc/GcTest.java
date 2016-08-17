package com.uc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class GcTest {
    public static void main(String[] args)
                throws ClassNotFoundException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        final String url = "jdbc:mysql://10.255.33.87:3308";
        final String user = "dev";
        final String pwd = "devtest123";
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    java.sql.Connection con = null;
                    while (true) {
                        try {
                            con = DriverManager.getConnection(url, user, pwd);
                            con.createStatement().executeQuery("select 1");
                            Thread.sleep(200);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (con != null)
                                try {
                                    con.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                        }
                    }
                }
            }.start();
        }
    }
}

