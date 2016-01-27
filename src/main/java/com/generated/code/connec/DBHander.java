package com.generated.code.connec;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.generated.code.entity.InfomationSchema;
import com.generated.code.entity.JavaBeanEntity;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.exception.NotFountExcetion;

public abstract class DBHander {
  protected String buildQuerySQL(String tableName) {
    return "SELECT * FROM `" + tableName + "`";
  }

  /**
   * 获取DB数据类型TOJava实体bean <br/>
   * 由各自的子类来实现
   * 
   * @param connection
   * @param tableName
   * @return
   * @throws SQLException
   * @throws NotFountExcetion
   */
  public abstract List<JavaBeanEntity> readDBTypeToJavaType(Connection connection, String tableName)
      throws SQLException, NotFountExcetion;

  /**
   * 获取所有表名称和视图
   * 
   * @param connection
   * @return
   * @throws SQLException
   */
  public abstract List<String> getTables(Connection connection) throws SQLException;

  public abstract List<String> getTables(Connection connection, boolean showView) throws SQLException;

  /**
   * 获取表的注释,如果tableName is null 返回所有表的注释
   * 
   * @param connection
   * @param showView 是否显示视图的评论
   * @param tableName 指定表
   * @return
   * @throws SQLException
   */
  public abstract Map<String, String> getTableComment(Connection connection, boolean showView, String tableName)
      throws SQLException;

  /**
   * 根据表名获取数据详细信息
   * 
   * @param connection
   * @param tableName
   * @return
   * @throws SQLException
   */
  public abstract List<InfomationSchema> getInfomationSchema(Connection connection, String tableName)
      throws SQLException;

  public List<SimpleJavaType> getDBTypeToJavaType(Connection connection, String tableName) throws SQLException {
    System.out.println("EXEC SQL:" + buildQuerySQL(tableName));
    List<SimpleJavaType> list = new ArrayList<SimpleJavaType>();
    try {
      PreparedStatement pStatement = connection.prepareStatement(buildQuerySQL(tableName));
      ResultSetMetaData metaData = pStatement.getMetaData();
      int count = metaData.getColumnCount();
      int i = 1;
      while (count >= i) {
        SimpleJavaType simpleJavaType = new SimpleJavaType();
        simpleJavaType.setAutoIncrement(metaData.isAutoIncrement(i));
        if (metaData.getColumnClassName(i).equals("[B")) {
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
        list.add(simpleJavaType);
        i++;
      }
    } catch (SQLException e) {
      throw e;
    }
    /**
     * 获取列注释
     */
    List<InfomationSchema> isList = getInfomationSchema(connection, tableName);
    for (int i = 0; i < list.size(); i++) {
      SimpleJavaType javaType = list.get(i);
      for (int j = 0; j < isList.size(); j++) {
        InfomationSchema is = isList.get(j);
        if (is.COLUMN_NAME.equals(javaType.getColumnName())) {
          javaType.setComment(is.COLUMN_COMMENT);
        }
      }
    }
    return list;
  }
}
