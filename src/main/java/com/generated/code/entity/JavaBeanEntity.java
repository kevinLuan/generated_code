package com.generated.code.entity;

import com.generated.code.util.StringUtils;


public class JavaBeanEntity extends JavaBeanBase {
  private String columnName;
  private JavaDataType dataType = null;
  private boolean isAutoIncrement;
  private String databaseName;
  private String fieldName;
  private int precision;// 精度
  private int scale;// 刻度
  private int columnDisplaySize;
  private String columnClassName;

  public JavaBeanEntity(JavaDataType dataType) {
    this.dataType = dataType;
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



  public String getFieldName() {
    if (fieldName == null && columnName != null) {
      if (StringUtils.is_a_z(columnName.charAt(0))) {
        return fieldName = columnName;
      } else if (StringUtils.isA_Z(columnName.charAt(0))) {
        return fieldName = ((char) (columnName.charAt(0) + 32)) + columnName.substring(1);
      }
    } else {
      return fieldName;
    }
    return columnName;
  }

  /**
   * 是否需要导入包
   */
  private boolean isImport = true;

  /**
   * 获取包名
   * 
   * @return
   */
  public String getImportPackageName() {
    if (isImport)
      return dataType.javaType;
    else {
      return null;
    }
  }

  public boolean isAutoIncrement() {
    return isAutoIncrement;
  }

  public void setAutoIncrement(boolean isAutoIncrement) {
    this.isAutoIncrement = isAutoIncrement;
  }

  public JavaDataType getDataType() {
    return dataType;
  }

  public void setDataType(JavaDataType dataType) {
    this.dataType = dataType;
  }

  public String getFiledType() {
    int lastIndex = this.dataType.javaType.lastIndexOf('.');
    return this.dataType.javaType.substring(lastIndex + 1);
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public boolean isImport() {
    return isImport;
  }

  public void setImport(boolean isImport) {
    this.isImport = isImport;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public void setPrecision(int precision) {
    this.precision = precision;
  }

  public void setScale(int scale) {
    this.scale = scale;
  }

  public void setColumnDisplaySize(int columnDisplaySize) {
    this.columnDisplaySize = columnDisplaySize;
  }

  public void setColumnClassName(String columnClassName) {
    this.columnClassName = columnClassName;
  }

  public int getPrecision() {
    return precision;
  }

  public int getScale() {
    return scale;
  }

  public int getColumnDisplaySize() {
    return columnDisplaySize;
  }

  public String getColumnClassName() {
    return columnClassName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  @Override
  public String getClassName() {
    if (StringUtils.is_a_z(getTableName().charAt(0))) {
      char chat = getTableName().charAt(0);
      return ((char) (chat - 32) + getTableName().substring(1));
    }
    return getTableName();
  }
}
