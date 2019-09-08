package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.generated.code.handler.DatabaseHandler;
import com.generated.code.handler.MySQLHandler;
import com.generated.code.util.Config;

import lombok.Getter;
import lombok.SneakyThrows;

public class MysqlConnector implements Connector {
	@Getter
	private Connection connection;
	private DatabaseHandler handler = new MySQLHandler();

	@SneakyThrows
	public MysqlConnector(Config config) {
		Class.forName(config.getDriver());
		this.connection = DriverManager.getConnection(config.getJdbcUrl(), config.getUserName(), config.getPwd());
	}

	public static Connector of(Config config) {
		return new MysqlConnector(config);
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
