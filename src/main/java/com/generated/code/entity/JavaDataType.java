package com.generated.code.entity;

import java.util.HashMap;
import java.util.Map;

public class JavaDataType implements Cloneable {
  public String sqlType;
  public Long length;
  public String javaType;
  public Integer code;

  public JavaDataType() {}

  public JavaDataType(String sqlType, Long length, String javaType, Integer code) {
    this.sqlType = sqlType;
    this.length = length;
    this.javaType = javaType;
    this.code = code;
  }

  public JavaDataType(String sqlType, String javaType, Integer code) {
    this.sqlType = sqlType;
    this.javaType = javaType;
    this.code = code;
  }

  public Map<String, JavaDataType> map = new HashMap<String, JavaDataType>();

  public void init() {
    map.put("VARCHAR", new JavaDataType("VARCHAR", "java.lang.String", 12));
    map.put("CHAR", new JavaDataType("CHAR", "java.lang.String", 1));
    map.put("BLOB", new JavaDataType("BLOB", "java.lang.Byte[]", -4));
    map.put("TEXT", new JavaDataType("TEXT", 65535l, "java.lang.String", -1));
    map.put("INTEGER", new JavaDataType("INTEGER", "java.lang.Integer", 4));
    // map.put("INTEGER",new JavaDataType("INTEGER", "java.lang.Long", 4));
    map.put("TINYINT", new JavaDataType("TINYINT", "java.lang.Integer", -6));
    map.put("SMALLINT", new JavaDataType("SMALLINT", "java.lang.Integer", 5));
    map.put("MEDIUMINT", new JavaDataType("MEDIUMINT", "java.lang.Integer", 4));
    map.put("BIT", new JavaDataType("BIT", "java.lang.Boolean ", -7));
    map.put("BIGINT", new JavaDataType("BIGINT", "java.lang.Long", -5));
    // map.put("BIGINT",new JavaDataType("BIGINT",
    // "java.math.BigInteger",-5));
    map.put("FLOAT", new JavaDataType("FLOAT", "java.lang.Float", 7));
    map.put("DOUBLE", new JavaDataType("DOUBLE", "java.lang.Double", 8));
    map.put("DECIMAL", new JavaDataType("DECIMAL ", "java.math.BigDecimal", 3));
    map.put("BOOLEAN", new JavaDataType("BOOLEAN", "java.lang.Integer ", -6));
    map.put("DATE", new JavaDataType("DATE", "java.sql.Date", 91));
    map.put("TIME", new JavaDataType("TIME", "java.sql.Time", 92));
    map.put("DATETIME", new JavaDataType("DATETIME", "java.sql.Timestamp", 93));
    map.put("TIMESTAMP", new JavaDataType("TIMESTAMP", "java.sql.Timestamp ", 93));
    map.put("YEAR", new JavaDataType("YEAR", "java.sql.Date", 91));
    map.put("ID", new JavaDataType("ID", "java.lang.Long", 4));

    map.put("BINARY", new JavaDataType("BINARY", "java.lang.byte[]", -2));
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
