package com.generated.code.connec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generated.code.entity.InfomationSchema;
import com.generated.code.entity.JavaBeanEntity;
import com.generated.code.entity.JavaDataType;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.exception.NotFountExcetion;

public class MySQLHander extends DBHander {
  JavaDataType javaDataType = new JavaDataType();
  {
    javaDataType.init();
  }

  public List<JavaBeanEntity> readDBTypeToJavaType(Connection connection, String tableName) throws SQLException,
      NotFountExcetion {
    List<JavaBeanEntity> list = new ArrayList<JavaBeanEntity>();
    System.out.println("exec sql :" + buildQuerySQL(tableName));
    try {
      PreparedStatement pStatement = connection.prepareStatement(buildQuerySQL(tableName), Statement.NO_GENERATED_KEYS);
      // java.sql.Types types;
      ResultSetMetaData metaData = pStatement.getMetaData();
      int count = metaData.getColumnCount();
      int i = 1;
      while (count >= i) {
        SimpleJavaType simpleJavaType = new SimpleJavaType();
        simpleJavaType.setAutoIncrement(metaData.isAutoIncrement(i));
        if (!metaData.getColumnClassName(i).equals("[B")) {
          simpleJavaType.setColumnClassName("java.lang.Byte[]");
        } else {
          simpleJavaType.setColumnClassName(metaData.getColumnClassName(i));
        }
        simpleJavaType.setColumnLabel(metaData.getColumnLabel(i));
        simpleJavaType.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
        simpleJavaType.setColumnName(metaData.getColumnName(i));
        simpleJavaType.setDatabaseName(metaData.getCatalogName(i));
        simpleJavaType.setPrecision(metaData.getPrecision(i));
        simpleJavaType.setScale(metaData.getScale(i));
        simpleJavaType.setTableName(metaData.getTableName(i));
        simpleJavaType.setColumnType(metaData.getColumnType(i));
        simpleJavaType.setColumnTypeName(metaData.getColumnTypeName(i));
        if (javaDataType.map.get(metaData.getColumnTypeName(i)) == null) {
          throw new NotFountExcetion("没有找到匹配的数据类型:" + metaData.getColumnTypeName(i));
        } else {
          JavaDataType dataType = (JavaDataType) javaDataType.map.get(metaData.getColumnTypeName(i)).clone();
          if (dataType != null) {
            JavaBeanEntity entity = new JavaBeanEntity(dataType);
            entity.setTableName(metaData.getTableName(i));
            entity.setAutoIncrement(metaData.isAutoIncrement(i));
            entity.setColumnName(metaData.getColumnName(i));
            entity.setDatabaseName(metaData.getCatalogName(i));
            entity.setPrecision(metaData.getPrecision(i));
            entity.setScale(metaData.getScale(i));
            entity.setColumnDisplaySize(metaData.getColumnDisplaySize(i));
            entity.setColumnClassName(metaData.getColumnClassName(i));
            list.add(entity);
          }
        }
        i++;
      }
    } catch (SQLException e) {
      throw e;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return list;
  }

  public List<String> getTables(Connection connection) throws SQLException {
    List<String> tableList = new ArrayList<String>();
    // SHOW TABLE STATUS from qstory_account
    String sql = "show tables;";
    try {
      PreparedStatement pStatement = connection.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();
      ResultSetMetaData metaData = pStatement.getMetaData();
      int count = metaData.getColumnCount();
      while (resultSet.next()) {
        int i = 1;
        while (count >= i) {
          tableList.add(resultSet.getString(i));
          i++;
        }
      }
    } catch (SQLException e) {
      throw e;
    }
    return tableList;
  }

  public List<String> getTables(Connection connection, boolean showView) throws SQLException {
    List<String> tableList = new ArrayList<String>();
    // SHOW TABLE STATUS from qstory_account
    String sql = "SHOW TABLE STATUS from " + connection.getCatalog() + ";";
    if (!showView) {
      sql = "SHOW TABLE STATUS from " + connection.getCatalog() + " WHERE Engine is not null and Version is not null";
    }
    try {
      PreparedStatement pStatement = connection.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();
      while (resultSet.next()) {
        tableList.add(resultSet.getString(1));
      }
    } catch (SQLException e) {
      throw e;
    }
    return tableList;
  }

  public Map<String, String> getTableComment(Connection connection, boolean showView, String tableName)
      throws SQLException {
    Map<String, String> map = new HashMap<String, String>();
    String sql =
        "SELECT table_name,TABLE_COMMENT from INFORMATION_SCHEMA.TABLES" + " WHERE table_schema = '"
            + connection.getCatalog() + "'";
    if (!showView) {
      sql += " and (Engine is not null and Version is not null)";
    }
    if (tableName != null) {
      sql += " and table_name='" + tableName + "'";
    }
    try {
      PreparedStatement pStatement = connection.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();
      while (resultSet.next()) {
        map.put(resultSet.getString(1), resultSet.getString(2));
      }
    } catch (SQLException e) {
      throw e;
    }
    return map;
  }

  public List<InfomationSchema> getInfomationSchema(Connection connection, String tableName) throws SQLException {
    String sql =
        "SELECT TABLE_NAME,COLUMN_NAME,COLUMN_DEFAULT,IS_NULLABLE,DATA_TYPE"
            + ",CHARACTER_MAXIMUM_LENGTH,COLUMN_KEY,COLUMN_COMMENT" + " from INFORMATION_SCHEMA.COLUMNS"
            + " WHERE table_schema = '" + connection.getCatalog() + "'" + " AND table_name = '" + tableName + "'";
    List<InfomationSchema> list = new ArrayList<InfomationSchema>();
    try {
      PreparedStatement pStatement = connection.prepareStatement(sql);
      ResultSet resultSet = pStatement.executeQuery();
      while (resultSet.next()) {
        InfomationSchema infoSchema = new InfomationSchema();
        infoSchema.TABLE_NAME = resultSet.getString("TABLE_NAME");
        infoSchema.COLUMN_NAME = resultSet.getString("COLUMN_NAME");
        infoSchema.COLUMN_DEFAULT = resultSet.getString("COLUMN_DEFAULT");
        infoSchema.IS_NULLABLE = resultSet.getString("IS_NULLABLE");
        infoSchema.DATA_TYPE = resultSet.getString("DATA_TYPE");
        infoSchema.CHARACTER_MAXIMUM_LENGTH = resultSet.getString("CHARACTER_MAXIMUM_LENGTH");
        infoSchema.COLUMN_KEY = resultSet.getString("COLUMN_KEY");
        infoSchema.COLUMN_COMMENT = resultSet.getString("COLUMN_COMMENT");
        list.add(infoSchema);
      }
    } catch (SQLException e) {
      throw e;
    }
    return list;
  }

}
