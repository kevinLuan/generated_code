package com.generated.code.entity;

public class MysqlDataType_Conversion_Java {
  // These MySQL Data Types:
  public String[] mysqlStrType = new String[] {"CHAR", "VARCHAR", "BLOB", "TEXT", "ENUM", "and SET"};
  /**
   * Can always be converted to these Java types:
   */
  public String[] javaStrType = new String[] {"java.lang.String", "java.io.InputStream", "java.io.Reader",
      "java.sql.Blob,java.sql.Clob"};

  public String[] mysqlNumberType = new String[] {"FLOAT", "REAL", "DOUBLE PRECISION", "NUMERIC", "DECIMAL", "TINYINT",
      "SMALLINT", "MEDIUMINT", "INTEGER", "BIGINT"};
  public String[] javaNumberType = new String[] {"java.lang.String", "java.lang.Short", "java.lang.Integer",
      "java.lang.Long", "java.lang.Double", "java.math.BigDecimal"};

  public String[] mysqlTimeType = new String[] {"DATE", "TIME", "DATETIME", "TIMESTAMP"};
  public String[] javaTimeType = new String[] {"java.lang.String", "java.sql.Date", "java.sql.Timestamp"};
}
