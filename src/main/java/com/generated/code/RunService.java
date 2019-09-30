package com.generated.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.generated.code.connec.Connector;
import com.generated.code.dao.GeneratedDaoImplInterface;
import com.generated.code.dao.GeneratedDaoInterface;
import com.generated.code.entity.GeneratedDaoInterfaceEntity;
import com.generated.code.entity.GeneratedDaoInterfaceImplEntity;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.util.Config;
import com.generated.code.util.FileUtils;

/**
 * 生成JAVA Code主运行类
 * 
 * @author kevin LUAN
 * @email kevin_Luan@126.com
 */
public class RunService {
	static Config config;
	static {
		try {
			InputStream is = RunService.class.getResourceAsStream("/conf.properties");
			Properties properties = new Properties();
			properties.load(is);
			config = Config.of(properties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		Connector connector = config.getConnector();
		connector.getTables().forEach((table) -> {
			 if(!table.startsWith("user_log")){
			 return;
			 }
			List<SimpleJavaType> typeList = connector.getColumns(table);
			generatedPOJO(typeList);
			generatedMybatisXml(typeList, false);
			// GeneratedProtobuf.toProto(typeList, POJO_PACK);
			generatedMapper(typeList);
			// generateHibernatePOJO(typeList);
			generatedDAO(typeList);
			generatedDAOImpl(typeList);
		});
		connector.close();
	}

	public static void generatedDAOImpl(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = GeneratedDaoImplInterface
				.buildDAOInterfaceImpl(new GeneratedDaoInterfaceImplEntity(typeList, config));
		String path = config.getCodePath("/dao/impl/" + javaType.getDAOImplClassName() + ".java");
		FileUtils.writeFile(path, content);
	}

	/**
	 * 生成DAO 接口
	 * 
	 * @param typeList
	 */
	public static void generatedDAO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = GeneratedDaoInterface.buildDAOInterface(new GeneratedDaoInterfaceEntity(typeList, config));
		String path = config.getCodePath("/dao/" + javaType.getDAOClassName() + ".java");
		FileUtils.writeFile(path, content);
	}

	/**
	 * 生成基本的POJO类
	 * 
	 * @param typeList
	 */
	public static void generatedPOJO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = new SimpleGeneratedBean().buildJavaBean(typeList, config);
		String path = config.getCodePath("/model/" + javaType.getClassName() + ".java");
		FileUtils.writeFile(path, content);
	}

	public static void generatedMapper(List<SimpleJavaType> typeList) {
		for (int i = 0; i < typeList.size(); i++) {
			SimpleJavaType javaType = typeList.get(i);
			javaType.setPackageInfo(config.getPojoPack());
			StringBuilder builder = new StringBuilder(1024);
			String fileName = GeneratedMybatis.toMapper(javaType, config, builder);
			FileUtils.writeFile(config.getCodePath("/mapper/" + fileName), builder.toString());
		}
	}

	public static void generatedMybatisXml(List<SimpleJavaType> typeList, boolean isProtoType) {
		SimpleJavaType javaType = typeList.get(0);
		javaType.setPackageInfo(config.getPojoPack());
		StringBuilder builder = new StringBuilder(1024);
		String fileName = GeneratedMybatis.toXml(typeList, config, builder, isProtoType);
		System.out.println(fileName);
		FileUtils.writeFile(config.getCodePath("/xml/" + fileName), builder.toString());

	}

	/**
	 * 生成Hibernate POJO类
	 * 
	 * @param typeList
	 */
	public static void generateHibernatePOJO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = new GeneratedHibernatePojo().buildJavaBean(typeList, config);
		String path = config.getCodePath("/model/" + javaType.getClassName() + ".java");
		FileUtils.writeFile(path, content);
	}

}
