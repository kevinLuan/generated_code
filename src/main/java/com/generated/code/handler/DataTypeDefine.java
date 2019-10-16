package com.generated.code.handler;

import lombok.Getter;

@Getter
public enum DataTypeDefine {
  BYTE("[B","java.lang.Byte[]"),//
  DATETIME("java.sql.Timestamp","java.util.Date");
  private DataTypeDefine(String sqlRawClassName,String javaClassName){
    this.sqlRawClassName=sqlRawClassName;
    this.javaClassName=javaClassName;
  }
  private String sqlRawClassName;
  private String javaClassName;

  /**
   * 根据JDBC返回原生的
   * @param sqlRawClassName
   * @return
   */
  public static DataTypeDefine getByClassName(String sqlRawClassName){
    for(DataTypeDefine define:values()){
      if(define.getSqlRawClassName().equals(sqlRawClassName)){
        return define;
      }
    }
    return null;
  }
}
