package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.generated.code.handler.DatabaseHandler;
import com.generated.code.handler.MySQLHandler;

import lombok.Getter;
import lombok.SneakyThrows;

public class MysqlConnector implements Connector {
	@Getter
	private Connection connection;
	private DatabaseHandler handler = new MySQLHandler();

	@SneakyThrows
	public Connector createConnector(String database, String user, String password, String host, int port) {
		System.out.println("获取数据库连接......");
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
		return this;
	}

	@Override
	public DatabaseHandler getHandler() {
		return handler;
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.connection = null;
		}
	}
}
