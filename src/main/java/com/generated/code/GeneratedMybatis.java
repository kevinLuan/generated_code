package com.generated.code;

import com.generated.code.entity.SimpleJavaType;

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

  /**
   * 生成Mybatis xml
   * 
   * @param javaType
   * @param mapperPackage
   * @param builder
   * @return 文件名
   */
  public static String toXml(SimpleJavaType javaType, String mapperPackage, StringBuilder builder) {
    builder.append(template.toString()).append("\n");
    builder.append(
        "<mapper namespace=\"" + mapperPackage + "." + javaType.getClassName() + "Mapper\">");
    builder.append("\n");
    builder
        .append("<!--POJO:" + javaType.getRawPackage() + "." + javaType.getClassName() + " -->\n");
    builder.append("</mapper>");
    return javaType.getClassName() + "Mapper.xml";
  }

  public static String toMapper(SimpleJavaType javaType, String mapperPackage,
      StringBuilder builder) {
    builder.append("package " + mapperPackage + ";").append("\n");
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
