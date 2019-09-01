package com.generated.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.generated.code.connec.DBHander;
import com.generated.code.connec.PostgreHander;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.util.FileUtils;
import com.google.gson.Gson;

import lombok.SneakyThrows;

public class PostgreTest {
	@SneakyThrows
	static Connection createConnection() {
		String host = "127.0.0.1";
		String port = "5000";
		String user = "root";
		String password = "123456";
		String database = "kevin";
		Class.forName("org.postgresql.Driver");
		String pattern = "jdbc:postgresql://{0}:{1}/{2}";
		String url = MessageFormat.format(pattern, host, port, database);
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
		return connection;
	}

	@SneakyThrows
	public static void main(String[] args) {
		Connection connection = createConnection();
		DBHander dbHander = new PostgreHander();
		List<String> tableList = dbHander.getTables(connection, false);
		tableList.forEach((table) -> {
			System.out.println(table);
			try {
				Map<String, String> map = dbHander.getTableComment(connection, false, table);
				System.out.println(map);
				List<SimpleJavaType> list = dbHander.getDBTypeToJavaType(connection, table);
				System.out.println(new Gson().toJson(list));
				generatedPOJO(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		});

	}

	public static final String HOME = "/data/code";

	public static void generatedPOJO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = new SimpleGeneratedBean().buildJavaBean(typeList, "com.zhaopin.model");
		FileUtils.writeFile(buildModelFilePath(javaType), content);
		System.out.println("generated simple POJO :" + buildModelFilePath(javaType));
	}

	private static String buildModelFilePath(SimpleJavaType javaType) {
		return HOME + "/model/" + javaType.getClassName() + ".java";
	}
}
