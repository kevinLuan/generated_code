package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.generated.code.handler.DBHandler;
import com.generated.code.handler.MySQLHandler;

import lombok.Getter;
import lombok.SneakyThrows;

public class MysqlConnector implements Connector {
	@Getter
	private Connection connection;
	private DBHandler handler = new MySQLHandler();

	@SneakyThrows
	public Connector createConnector(String database, String user, String password, String host, int port)
			throws SQLException {
		System.out.println("获取数据库连接......");
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
		return this;
	}

	@Override
	public DBHandler getHandler() {
		return handler;
	}
}
