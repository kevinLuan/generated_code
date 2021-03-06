package com.generated.code.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generated.code.entity.InfomationSchema;
import com.generated.code.natives.MysqlNative;
import com.generated.code.natives.Native;

public class MySQLHandler extends DatabaseHandler {

	@Override
	Native getNative() {
		return new MysqlNative();
	}

	protected String buildQuerySQL(String tableName) {
		return "SELECT * FROM `" + tableName + "`";
	}

	public List<String> getTables(Connection connection) throws SQLException {
		List<String> tableList = new ArrayList<String>();
		// SHOW TABLE STATUS from qstory_account
		String sql = "show tables;";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			// ResultSetMetaData metaData = pStatement.getMetaData();
			// int count = metaData.getColumnCount();
			while (resultSet.next()) {
				tableList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw e;
		}
		return tableList;
	}

	public List<String> getTables(Connection connection, boolean showView) throws SQLException {
		List<String> tableList = new ArrayList<String>();
		// SHOW TABLE STATUS from qstory_account
		String sql = "SHOW TABLE STATUS from " + connection.getCatalog() + ";";
		if (!showView) {
			sql = "SHOW TABLE STATUS from " + connection.getCatalog() + " WHERE Engine is not null and Version is not null";
		}
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				tableList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw e;
		}
		return tableList;
	}

	public Map<String, String> getTableComment(Connection connection, String tableName) throws SQLException {
		return getTableComment(connection, false, tableName);
	}

	public Map<String, String> getTableComment(Connection connection, boolean showView, String tableName)
			throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "SELECT table_name,TABLE_COMMENT from INFORMATION_SCHEMA.TABLES" + " WHERE table_schema = '"
				+ connection.getCatalog() + "'";
		if (!showView) {
			sql += " and (Engine is not null and Version is not null)";
		}
		if (tableName != null) {
			sql += " and table_name='" + tableName + "'";
		}
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				map.put(resultSet.getString(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			throw e;
		}
		return map;
	}

	public List<InfomationSchema> getInfomationSchema(Connection connection, String tableName) throws SQLException {
		String sql = "SELECT TABLE_NAME,COLUMN_NAME,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE"
				+ ",CHARACTER_MAXIMUM_LENGTH,COLUMN_KEY,COLUMN_COMMENT" + " from INFORMATION_SCHEMA.COLUMNS"
				+ " WHERE table_schema = '" + connection.getCatalog() + "'" + " AND table_name = '" + tableName + "'";
		List<InfomationSchema> list = new ArrayList<InfomationSchema>();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				InfomationSchema infoSchema = new InfomationSchema();
				infoSchema.TABLE_NAME = resultSet.getString("TABLE_NAME");
				infoSchema.COLUMN_NAME = resultSet.getString("COLUMN_NAME");
				infoSchema.COLUMN_DEFAULT = resultSet.getString("COLUMN_DEFAULT");
				infoSchema.IS_NULLABLE = resultSet.getString("IS_NULLABLE");
				infoSchema.DATA_TYPE = resultSet.getString("DATA_TYPE");
				infoSchema.CHARACTER_MAXIMUM_LENGTH = resultSet.getString("CHARACTER_MAXIMUM_LENGTH");
				infoSchema.COLUMN_KEY = resultSet.getString("COLUMN_KEY");
				infoSchema.COLUMN_COMMENT = resultSet.getString("COLUMN_COMMENT");
				list.add(infoSchema);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

}
