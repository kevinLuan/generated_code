package com.generated.code.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generated.code.entity.InfomationSchema;
import com.generated.code.natives.Native;
import com.generated.code.natives.PostgreNative;

public class PostgreHandler extends DatabaseHandler {

	protected String buildQuerySQL(String tableName) {
		return "SELECT * FROM \"" + tableName + "\"";
	}

	@Override
	Native getNative() {
		return new PostgreNative();
	}

	public List<String> getTables(Connection connection) throws SQLException {
		List<String> tableList = new ArrayList<String>();
		String sql = "select relname as tabname from pg_class c WHERE relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%' order by relname";
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			ResultSetMetaData metaData = pStatement.getMetaData();
			int count = metaData.getColumnCount();
			while (resultSet.next()) {
				int i = 1;
				while (count >= i) {
					tableList.add(resultSet.getString(i));
					i++;
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return tableList;
	}

	public Map<String, String> getTableComment(Connection connection, String tableName) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select relname as tabname, cast( obj_description(relfilenode, 'pg_class') as varchar) as comment"
				+ " from pg_class c where relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%'";
		if (tableName != null) {
			sql += " and relname='" + tableName + "'";
		}
		sql += " order by relname";
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
		String sql = "SELECT col_description(a.attrelid,a.attnum) as COMMENT,"//
				+ "format_type(a.atttypid,a.atttypmod) as DATA_TYPE,"//
				+ "a.attname as NAME,"//
				+ "a.attnotnull as IS_NULLABLE"//
				+ " FROM pg_class as c,pg_attribute as a where c.relname = '" + tableName
				+ "' and a.attrelid = c.oid and a.attnum>0";
		List<InfomationSchema> list = new ArrayList<InfomationSchema>();
		try {
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				InfomationSchema infoSchema = new InfomationSchema();
				infoSchema.TABLE_NAME = tableName;
				infoSchema.COLUMN_NAME = resultSet.getString("NAME");
				// infoSchema.COLUMN_DEFAULT = resultSet.getString("COLUMN_DEFAULT");
				infoSchema.IS_NULLABLE = resultSet.getString("IS_NULLABLE");//// t:notnull
																																		//// ,f:null
				infoSchema.DATA_TYPE = resultSet.getString("DATA_TYPE");
				// infoSchema.CHARACTER_MAXIMUM_LENGTH =
				// resultSet.getString("CHARACTER_MAXIMUM_LENGTH");
				// infoSchema.COLUMN_KEY = resultSet.getString("COLUMN_KEY");
				infoSchema.COLUMN_COMMENT = resultSet.getString("COMMENT");
				list.add(infoSchema);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

}
