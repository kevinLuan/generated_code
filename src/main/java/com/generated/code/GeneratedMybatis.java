package com.generated.code;

import java.util.List;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.util.Config;

public class GeneratedMybatis {
	private static StringBuffer template = new StringBuffer(1024);
	static {
		init();
	}

	static void init() {
		template.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		template.append("\n");
		template.append("<!DOCTYPE mapper").append("\n");
		template.append("\t");
		template.append("PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
		template.append("\n");
		template.append("\t");
		template.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
	}

	static final String INSERT_SQL_TEMP = "INSERT INTO %s \n(%s)\nVALUES\n(%s)";
	static final String MYBATIS_INSERT = "<insert id='save' parameterType='%s' keyProperty='id_' useGeneratedKeys='true'>"//
			+ "\n\t<![CDATA[\n\t%s\n\t]]>\n"//
			+ "</insert>";

	static final String SELECT_SQL_TEMP = "SELECT %s FROM %s where id=#{id}";
	static final String mybatis_select = "<select id='getById' resultType='%s'>"//
			+ "\n\t<![CDATA[\n\t%s\n\t]]>\n"//
			+ "</select>";

	static final String UPDATE_SQL_TEMP = "UPDATE %s \nSET \n%s\nWHERE id=#{id};";
	static final String MYBATIS_UPDATE = //
			"<update id='updateValue' parameterType='%s'>\n"//
					+ "\n\t<![CDATA[\n\t%s\n\t]]>\n"//
					+ "</update>";//

	static final String DELETE_SQL_TEMP = "DELETE FROM %s WHERE id=#{id}";
	static final String MYBATIS_DELETE = "<delete id='deleteById' parameterType='int'>"//
			+ "\n\t<![CDATA[\n\t%s\n\t]]>\n"//
			+ "</delete>";

	/**
	 * 生成Mybatis xml
	 * 
	 * @param javaType
	 * @param mapperPackage
	 * @param builder
	 * @return 文件名
	 */
	public static String toXml(List<SimpleJavaType> javaTypes, Config config, StringBuilder builder) {
		SimpleJavaType javaType = javaTypes.get(0);
		// 名称使用GRPC生成的Model.builder
		String modelName = config.getPojoPack() + "." + javaType.getClassName() + "$Builder";
		String mapperName = javaType.getClassName() + "Mapper";
		builder.append(template.toString()).append("\n");
		builder.append(String.format("<mapper namespace=\"%s.%s\">\n", config.getMapperPack(), mapperName));
		String columns = buildAllColumns(javaTypes);
		String fields = buildAllField(javaTypes);

		// <parameterMap type="xx.$Builder" id="xxxMap">
		// <parameter property="startTime"
		// typeHandler="com.look.mybatis.hander.DateTimeTypeHander"/>
		// </parameterMap>
		addParameterMap(javaTypes, config.getPojoPack(), builder, javaType);
		addResultMap(javaTypes, config.getPojoPack(), builder, javaType);
		builder.append("<!--生成模板方法-->").append("\n");
		builder.append("<!--").append("\n");
		{// 生成insert语句
			String insertSql = String.format(INSERT_SQL_TEMP, javaType.getTableName(), columns, fields);
			String mybatisInsert = String.format(MYBATIS_INSERT, modelName, insertSql);
			builder.append(mybatisInsert.replace('\'', '"')).append("\n");
		}
		{// 生成select语句
			String selectSql = String.format(SELECT_SQL_TEMP, columns, javaType.getTableName());
			String mybatisSelect = String.format(mybatis_select, modelName, selectSql);
			builder.append(mybatisSelect.replace('\'', '"')).append("\n");
		}
		{// 生成update语句
			String set_columns = buildUpdateSetColumns(javaTypes);
			String updateSql = String.format(UPDATE_SQL_TEMP, javaType.getTableName(), set_columns);
			String mybatisUpdate = String.format(MYBATIS_UPDATE, modelName, updateSql);
			builder.append(mybatisUpdate.replace('\'', '"')).append("\n");
		}
		{// 生成delete语句
			String deleteSql = String.format(DELETE_SQL_TEMP, javaType.getTableName());
			String mybatisDelete = String.format(MYBATIS_DELETE, deleteSql);
			builder.append(mybatisDelete.replace('\'', '"')).append("\n");
		}
		builder.append("-->").append("\n");
		builder.append("</mapper>");
		return javaType.getClassName() + "Mapper.xml";
	}

	private static void addParameterMap(List<SimpleJavaType> javaTypes, String modelPack, StringBuilder builder,
			SimpleJavaType javaType) {
		builder.append(String.format("<parameterMap type=\"%s.%s$Builder\" id=\"%sMap\">\n", modelPack,
				javaType.getClassName(), javaType.getClassName()));
		for (int i = 0; i < javaTypes.size(); i++) {
			SimpleJavaType type = javaTypes.get(i);
			builder.append("\t");
			if (type.getColumnTypeName() == "DATETIME") {
				builder.append("<parameter property=\"" + type.getFieldName()
						+ "\" typeHandler=\"com.look.mybatis.hander.DateTimeTypeHander\"/>");
			} else {
				builder.append("<parameter property=\"" + type.getFieldName() + "\"/>");
			}
			builder.append("\n");
		}
		builder.append("</parameterMap>\n");
	}

	private static void addResultMap(List<SimpleJavaType> javaTypes, String modelPack, StringBuilder builder,
			SimpleJavaType javaType) {
		builder.append(String.format("<resultMap type=\"%s.%s$Builder\" id=\"%sResultMap\">\n", modelPack,
				javaType.getClassName(), javaType.getClassName()));
		builder.append("\t<id column=\"id\" property=\"id\"/>\n");
		for (int i = 0; i < javaTypes.size(); i++) {
			SimpleJavaType type = javaTypes.get(i);
			builder.append("\t");
			if (type.getColumnTypeName() == "DATETIME") {
				builder.append(String.format(
						"<result column=\"%s\" property=\"%s\" typeHandler=\"com.look.mybatis.hander.DateTimeTypeHander\"/>",
						type.getColumnName(), type.getFieldName()));
			} else {
				if (!type.getColumnName().equals("id")) {
					builder.append(
							String.format("<result column=\"%s\" property=\"%s\"/>", type.getColumnName(), type.getFieldName()));
				}
			}
			builder.append("\n");
		}
		builder.append("</resultMap>\n");
	}

	private static String buildUpdateSetColumns(List<SimpleJavaType> javaTypes) {
		StringBuilder xmlBuilder = new StringBuilder();
		for (int i = 0; i < javaTypes.size(); i++) {
			SimpleJavaType type = javaTypes.get(i);
			if (type.getColumnName().equals("id")) {
				continue;
			}
			xmlBuilder.append(String.format("%s=#{%s}", type.getColumnName(), type.getFieldName()));
			xmlBuilder.append(",");
		}
		return xmlBuilder.substring(0, xmlBuilder.length() - 1);
	}

	private static String buildAllColumns(List<SimpleJavaType> javaTypes) {
		StringBuilder xmlBuilder = new StringBuilder();
		for (int i = 0; i < javaTypes.size(); i++) {
			SimpleJavaType type = javaTypes.get(i);
			if (i == javaTypes.size() - 1) {
				xmlBuilder.append(type.getColumnName());
			} else {
				xmlBuilder.append(type.getColumnName()).append(",");
			}
		}
		return xmlBuilder.toString();
	}

	private static String buildAllField(List<SimpleJavaType> javaTypes) {
		StringBuilder xmlBuilder = new StringBuilder();
		for (int i = 0; i < javaTypes.size(); i++) {
			SimpleJavaType type = javaTypes.get(i);
			if (i == javaTypes.size() - 1) {
				xmlBuilder.append(String.format("#{%s}", type.getFieldName()));
			} else {
				xmlBuilder.append(String.format("#{%s}", type.getFieldName())).append(",");
			}
		}
		return xmlBuilder.toString();
	}

	public static String toMapper(SimpleJavaType javaType, Config config, StringBuilder builder) {
		builder.append("package " + config.getMapperPack() + ";").append("\n");
		builder.append("import " + javaType.getRawPackage() + "." + javaType.getClassName() + ";");
		builder.append("\n");
		builder.append("import com.mogujie.trade.db.DataSourceRouting;\n");
		builder.append("\n");
		builder.append("@DataSourceRouting(table = \"" + javaType.getTableName() + "\", dataSource = \""
				+ javaType.getDatabaseName() + "\", isReadWriteSplitting = false)");
		builder.append("\n");
		builder.append("public interface " + javaType.getClassName() + "Mapper { \n" + "}\n");
		return javaType.getClassName() + "Mapper.java";
	}
}
