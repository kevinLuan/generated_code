package com.generated.code;

import java.sql.SQLException;
import java.util.List;

import com.generated.code.connec.Connector;
import com.generated.code.connec.Connectors;
import com.generated.code.dao.GeneratedDaoImplInterface;
import com.generated.code.dao.GeneratedDaoInterface;
import com.generated.code.entity.GeneratedDaoInterfaceEntity;
import com.generated.code.entity.GeneratedDaoInterfaceImplEntity;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.util.FileUtils;

/**
 * 生成JAVA Code主运行类
 * 
 * @author kevin LUAN
 * @email kevin_Luan@126.com
 */
public class RunService {
	/**
	 * generated java code path
	 */
	public static final String HOME = "/data/code";
	public static final String BASE_PACK = "com.lyh";
	static String POJO_PACK = "com.lyh.customs.model";
	/* DAO接口的包路径 */
	static String DAO_PACK = "com.lyh.customs.mapper";
	/* DAO实现类的包路径 */
	static String DAO_IMPL_PACK = "com.lyh.customs.dao";

	public static void main(String[] args) throws SQLException {
		Connector connector = createPostgre();
		// createMysql();
		connector.getTables().forEach((table) -> {
			List<SimpleJavaType> typeList = connector.getColumns(table);
			generatedPOJO(typeList);
			generatedMybatisXml(typeList);
			// GeneratedProtobuf.toProto(typeList, POJO_PACK);
			generatedMapper(typeList);
			// generateHibernatePOJO(typeList);
			generatedDAO(typeList);
			generatedDAOImpl(typeList);
		});
		connector.close();
	}

	static Connector createMysql() throws SQLException {
		String database = "open";// 数据库名
		String user = "root";
		String password = "123456";
		String host = "127.0.0.1";
		int port = 3306;
		return Connectors.createMysqlConnector(database, user, password, host, port);
	}

	static Connector createPostgre() throws SQLException {
		String host = "127.0.0.1";
		int port = 5000;
		String user = "root";
		String password = "123456";
		String database = "kevin";
		return Connectors.createPostgreConnector(database, user, password, host, port);
	}

	/**
	 * 生成DAO 接口的实现类
	 * 
	 * @param typeList
	 */
	public static void generatedDAOImpl(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = GeneratedDaoImplInterface
				.buildDAOInterfaceImpl(new GeneratedDaoInterfaceImplEntity(typeList, DAO_IMPL_PACK, DAO_PACK));
		FileUtils.writeFile(buildDAOImplFilePath(javaType), content);
		System.out.println("generated dao implements :" + buildModelFilePath(javaType));
	}

	/**
	 * 生成DAO 接口
	 * 
	 * @param typeList
	 */
	public static void generatedDAO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = GeneratedDaoInterface.buildDAOInterface(new GeneratedDaoInterfaceEntity(typeList, DAO_PACK));
		FileUtils.writeFile(buildDAOFilePath(javaType), content);
		System.out.println("generated dao :" + buildModelFilePath(javaType));
	}

	/**
	 * 生成基本的POJO类
	 * 
	 * @param typeList
	 */
	public static void generatedPOJO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = new SimpleGeneratedBean().buildJavaBean(typeList, POJO_PACK);
		FileUtils.writeFile(buildModelFilePath(javaType), content);
		System.out.println("generated simple POJO :" + buildModelFilePath(javaType));
	}

	public static void generatedMapper(List<SimpleJavaType> typeList) {
		for (int i = 0; i < typeList.size(); i++) {
			SimpleJavaType javaType = typeList.get(i);
			javaType.setPackageInfo(POJO_PACK);
			StringBuilder builder = new StringBuilder(1024);
			String fileName = GeneratedMybatis.toMapper(javaType, DAO_PACK, builder);
			FileUtils.writeFile(HOME + "/mapper/" + fileName, builder.toString());
		}
	}

	public static void generatedMybatisXml(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		javaType.setPackageInfo(POJO_PACK);
		StringBuilder builder = new StringBuilder(1024);
		String fileName = GeneratedMybatis.toXml(typeList, POJO_PACK, DAO_PACK, builder);
		System.out.println(fileName);
		FileUtils.writeFile(HOME + "/xml/" + fileName, builder.toString());

	}

	/**
	 * 生成Hibernate POJO类
	 * 
	 * @param typeList
	 */
	public static void generateHibernatePOJO(List<SimpleJavaType> typeList) {
		SimpleJavaType javaType = typeList.get(0);
		String content = new GeneratedHibernatePojo().buildJavaBean(typeList, POJO_PACK);
		FileUtils.writeFile(buildModelFilePath(javaType), content);
		System.out.println("generated Hibernate POJO :" + buildModelFilePath(javaType));
	}

	private static String buildModelFilePath(SimpleJavaType javaType) {
		return HOME + "/model/" + javaType.getClassName() + ".java";
	}

	private static String buildDAOFilePath(SimpleJavaType javaType) {
		return HOME + "/dao/" + javaType.getDAOClassName() + ".java";
	}

	private static String buildDAOImplFilePath(SimpleJavaType javaType) {
		return HOME + "/dao/impl/" + javaType.getDAOImplClassName() + ".java";
	}
}
