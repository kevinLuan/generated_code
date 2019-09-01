package com.generated.code.connec;

import java.sql.SQLException;

public class Connectors {
	public static Connector createMysqlConnector(String database, String user, String password, String host, int port)
			throws SQLException {
		return new MysqlConnector().createConnector(database, user, password, host, port);
	}

	public static Connector createPostgreConnector(String database, String user, String password, String host, int port)
			throws SQLException {
		return new PostgreConnector().createConnector(database, user, password, host, port);
	}
}
