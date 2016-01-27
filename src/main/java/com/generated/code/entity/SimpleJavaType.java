package com.generated.code.entity;

import com.generated.code.util.StringUtils;


/**
 * 通过 JDBC提供的类型处理
 * 
 * @Description
 * @author kevin LUAN
 * @Date 2011-11-20
 */
public class SimpleJavaType extends JavaBeanBase {
  private String columnClassName;
  private String columnLabel;
  private int precision;// 精度
  private int scale;// 刻度
  private int columnDisplaySize;
  private String tableName;
  private String databaseName;
  private boolean isAutoIncrement;
  private String columnName;

  private int columnType;// DB
  private String fieldName;
  /**
   * 列注释
   */
  private String comment;
  /**
   * 是否需要导入包
   */
  private boolean isImport = true;
  private String columnTypeName;

  public String getColumnClassName() {
    return columnClassName;
  }

  public void setColumnClassName(String columnClassName) {
    this.columnClassName = columnClassName;
  }

  public String getColumnLabel() {
    return columnLabel;
  }

  public void setColumnLabel(String columnLabel) {
    this.columnLabel = columnLabel;
  }

  public int getPrecision() {
    return precision;
  }

  public void setPrecision(int precision) {
    this.precision = precision;
  }

  public int getScale() {
    return scale;
  }

  public void setScale(int scale) {
    this.scale = scale;
  }

  public int getColumnDisplaySize() {
    return columnDisplaySize;
  }

  public void setColumnDisplaySize(int columnDisplaySize) {
    this.columnDisplaySize = columnDisplaySize;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public boolean isAutoIncrement() {
    return isAutoIncrement;
  }

  public void setAutoIncrement(boolean isAutoIncrement) {
    this.isAutoIncrement = isAutoIncrement;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public int getColumnType() {
    return columnType;
  }

  public void setColumnType(int columnType) {
    this.columnType = columnType;
  }

  public String getColumnTypeName() {
    return columnTypeName;
  }

  public void setColumnTypeName(String columnTypeName) {
    this.columnTypeName = columnTypeName;
  }

  /**
   * 获取生成属性的getter方法名称
   * 
   * @return
   */
  public String getGetterMethodName() {
    if (StringUtils.is_a_z(getFieldName().charAt(0))) {
      return "get" + ((char) (fieldName.charAt(0) - 32)) + this.fieldName.substring(1);
    }
    return "get" + fieldName;
  }

  /**
   * 获取生成属性的setter方法名称
   * 
   * @return
   */
  public String getSetterMethodName() {
    if (StringUtils.is_a_z(getFieldName().charAt(0))) {
      return "set" + ((char) (fieldName.charAt(0) - 32)) + this.fieldName.substring(1);
    }
    return "set" + fieldName;
  }

  public String getFieldType() {
    int lastIndex = columnClassName.lastIndexOf(".");
    return columnClassName.substring(lastIndex + 1);
  }

  public String getFieldName() {
    if (fieldName == null && columnName != null) {
      String str = StringUtils.processStr_(columnName);
      if (StringUtils.is_a_z(str.charAt(0))) {
        return fieldName = str;
      } else if (StringUtils.isA_Z(str.charAt(0))) {
        return fieldName = ((char) (str.charAt(0) + 32)) + str.substring(1);
      }

    } else {
      return fieldName;
    }
    return columnName;
  }

  /**
   * 获取包名
   * 
   * @return
   */
  public String getImportPackageName() {
    if (isImport)
      return columnClassName;
    else {
      return null;
    }
  }

  public boolean isImport() {
    return isImport;
  }

  public void setImport(boolean bool) {
    this.isImport = bool;
  }

  @Override
  public String getClassName() {
    return StringUtils.processStr_(getTableName());
  }

  public String getDAOClassName() {
    return getClassName() + "DAO";
  }

  public String getDAOImplClassName() {
    return getClassName() + "DAOImpl";
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
