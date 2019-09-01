package com.generated.code.connec;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.generated.code.entity.SimpleJavaType;
import com.generated.code.handler.DBHandler;

public interface Connector {
	Connector createConnector(String database, String user, String password, String host, int port) throws SQLException;

	DBHandler getHandler();

	Connection getConnection();

	default List<String> getTables() throws SQLException {
		return getHandler().getTables(getConnection());
	}

	default Map<String, String> getTableComment(String table) throws SQLException {
		return getHandler().getTableComment(getConnection(), false, table);
	}

	default List<SimpleJavaType> getColumns(String table) throws SQLException {
		return getHandler().getDBTypeToJavaType(getConnection(), table);
	}

	void close();
}
