package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import com.generated.code.handler.DBHandler;
import com.generated.code.handler.PostgreHandler;

import lombok.Getter;
import lombok.SneakyThrows;

public class PostgreConnector implements Connector {
	@Getter
	private Connection connection;
	private DBHandler handler = new PostgreHandler();

	@Override
	@SneakyThrows
	public Connector createConnector(String database, String user, String password, String host, int port)
			throws SQLException {
		Class.forName("org.postgresql.Driver");
		String pattern = "jdbc:postgresql://{0}:{1}/{2}";
		String url = MessageFormat.format(pattern, host, String.valueOf(port), database);
		this.connection = DriverManager.getConnection(url, user, password);
		return this;
	}

	@Override
	public DBHandler getHandler() {
		return handler;
	}

}
