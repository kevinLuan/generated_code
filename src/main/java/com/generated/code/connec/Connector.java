package com.generated.code.connec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import com.generated.code.entity.SimpleJavaType;
import com.generated.code.handler.DatabaseHandler;
import com.generated.code.util.Config;

import lombok.SneakyThrows;

/**
 * 创建数据库连接器
 * 
 * @author SHOUSHEN LUAN
 */
public interface Connector {
	DatabaseHandler getHandler();

	Connection getConnection();
	
	@SneakyThrows
	default List<String> getTables() {
		return getHandler().getTables(getConnection());
	}

	@SneakyThrows
	default Map<String, String> getTableComment(String table) {
		return getHandler().getTableComment(getConnection(), table);
	}

	@SneakyThrows
	default List<SimpleJavaType> getColumns(String table) {
		return getHandler().getDBTypeToJavaType(getConnection(), table);
	}

	void close();
}
