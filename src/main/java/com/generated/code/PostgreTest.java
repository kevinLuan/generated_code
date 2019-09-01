package com.generated.code;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.generated.code.connec.Connector;
import com.generated.code.connec.Connectors;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.util.FileUtils;
import com.google.gson.Gson;

import lombok.SneakyThrows;

public class PostgreTest {
	@SneakyThrows
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 5000;
		String user = "root";
		String password = "123456";
		String database = "kevin";
		Connector connector = Connectors.createPostgreConnector(database, user, password, host, port);
		List<String> tableList = connector.getTables();
		tableList.forEach((table) -> {
			System.out.println(table);
			try {
				Map<String, String> map = connector.getTableComment(table);
				System.out.println(map);
				List<SimpleJavaType> list = connector.getColumns(table);
				System.out.println(new Gson().toJson(list));
				generatedPOJO(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		});
		connector.close();

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
