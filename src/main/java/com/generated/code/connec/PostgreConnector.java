package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import com.generated.code.handler.DatabaseHandler;
import com.generated.code.handler.PostgreHandler;

import lombok.Getter;
import lombok.SneakyThrows;

public class PostgreConnector implements Connector {
	@Getter
	private Connection connection;
	private DatabaseHandler handler = new PostgreHandler();

	@Override
	@SneakyThrows
	public Connector createConnector(String database, String user, String password, String host, int port) {
		Class.forName("org.postgresql.Driver");
		String pattern = "jdbc:postgresql://{0}:{1}/{2}";
		String url = MessageFormat.format(pattern, host, String.valueOf(port), database);
		this.connection = DriverManager.getConnection(url, user, password);
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
