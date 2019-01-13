package com.generated.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.util.FileUtils;

/**
 * 生成protobuf文件定义
 * 
 * @author SHOUSHEN LUAN
 *
 */
public class GeneratedProtobuf {
  // 定义数据类型映射<Mysql数据类型,Proto数据类型>
  private static Map<String, String> types = new HashMap<String, String>();
  static {
    types.put("INT", "int32");
    types.put("TINYINT", "int32");
    types.put("VARCHAR", "string");
    types.put("TEXT", "string");

    types.put("BIGINT", "int64");
    types.put("DATE", "int64");
    types.put("DATETIME", "int64");
    types.put("TIMESTAMP", "int64");
    
    types.put("BIGINT UNSIGNED", "int64");
    types.put("SMALLINT", "int32");
  }

  public static void toProto(List<SimpleJavaType> typeList, String pack) {
    SimpleJavaType javaType = typeList.get(0);
    StringBuilder builder = new StringBuilder();
    builder.append("syntax = \"proto3\";").append("\n");
    builder.append("option java_multiple_files = true;").append("\n");
    builder.append("option java_package = \"" + pack + "\";").append("\n");
    builder.append("option objc_class_prefix = \"HLW\";").append("\n");
    builder.append("option java_outer_classname = \"" + javaType.getClassName() + "Model\";")
        .append("\n");
    builder.append("package model;").append("\n");

    builder.append("//").append("\n");
    builder.append("message " + javaType.getClassName() + "{").append("\n");
    for (int i = 0; i < typeList.size(); i++) {
      SimpleJavaType simpleJavaType = typeList.get(i);
      builder.append("//" + simpleJavaType.getComment()).append("\n");
      builder.append(toField(simpleJavaType, i + 1));
    }
    builder.append("}").append("\n");
    FileUtils.writeFile("/data/code/proto/model/" + javaType.getTableName() + "_model.proto",
        builder.toString());
  }

  private static String toField(SimpleJavaType javaType, int value) {
    if (types.containsKey(javaType.getColumnTypeName())) {
      String protoType = types.get(javaType.getColumnTypeName());
      String result = protoType + " " + javaType.getColumnName() + "=" + value + ";\n\n";
      return result;
    } else {
      System.err
          .println("不支持的类型:" + javaType.getColumnTypeName() + " " + javaType.getColumnName() + ";");
      return "//" + javaType.getColumnTypeName() + " " + javaType.getColumnName() + "=" + value
          + ";\n\n";
    }
  }


}
