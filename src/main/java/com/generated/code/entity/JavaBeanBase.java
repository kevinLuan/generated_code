package com.generated.code.entity;

public abstract class JavaBeanBase {
  private String tableName;
  /**
   * 设置生成的包
   */
  private String pack;


  public abstract String getClassName();

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getPackageInfo() {
    return pack;
  }

  public void setPackageInfo(String pack) {
    if (pack != null) {
      if (pack.startsWith("package ") && pack.endsWith(";")) {
        this.pack = pack;
      } else if (pack.startsWith("package ")) {
        this.pack = pack + ";";
      } else if (pack.endsWith(";")) {
        this.pack = "package " + pack;
      } else {
        this.pack = "package " + pack + ";";
      }
    }
  }
}
