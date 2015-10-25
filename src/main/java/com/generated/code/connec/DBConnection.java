package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  public static Connection getMySQLConnection(String dbName, String userName, String userPwd, String ip, Integer port)
      throws SQLException {
    try {
      System.out.println("获取数据库连接......");
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbName, userName, userPwd);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      throw e;
    }
    System.out.println("获取数据库连接失败......");
    return null;
  }
}
