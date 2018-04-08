package com.generated.code;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.generated.code.GeneratedHibernatePojo;
import com.generated.code.GeneratedJavaBean;
import com.generated.code.SimpleGeneratedBean;
import com.generated.code.connec.DBConnection;
import com.generated.code.connec.DBHander;
import com.generated.code.connec.MySQLHander;
import com.generated.code.dao.GeneratedDaoImplInterface;
import com.generated.code.dao.GeneratedDaoInterface;
import com.generated.code.entity.GeneratedDaoInterfaceEntity;
import com.generated.code.entity.GeneratedDaoInterfaceImplEntity;
import com.generated.code.entity.JavaBeanEntity;
import com.generated.code.entity.SimpleJavaType;
import com.generated.code.exception.NotFountExcetion;
import com.generated.code.util.FileUtils;

/**
 * 生成JAVA Code主运行类
 * 
 * @author kevin LUAN
 * @email kevin_Luan@126.com
 *
 */
public class RunService {
  /**
   * generated java code path
   */
  public static final String HOME = "/data/code";
  static DBHander dbHander = new MySQLHander();
  /* POJO类的包路径 */
  static String POJO_PACK = "com.look.coupon.proto.model";
  /* DAO接口的包路径 */
  static String DAO_PACK = "com.look.coupon.mapper";
  /* DAO实现类的包路径 */
  static String DAO_IMPL_PACK = "ccom.xxx.order.dao";

  public static void main(String[] args) throws SQLException {
    String datebase = "coupon";// 数据库名
    String user = "root";
    String pwd = "123456";
    String host = "localhost";
    int port = 3306;
    Connection connection = DBConnection.getMySQLConnection(datebase, user, pwd, host, port);


    List<String> tableList = dbHander.getTables(connection, false);
    for (int i = 0; i < tableList.size(); i++) {
      List<SimpleJavaType> typeList = dbHander.getDBTypeToJavaType(connection, tableList.get(i));
      if (typeList != null) {
        // generatedPOJO(typeList);
        generatedMybatisXml(typeList);
        GeneratedProtobuf.toProto(typeList, POJO_PACK);
        generatedMapper(typeList);
        // generateHibernatePOJO(typeList);
        // generatedDAO(typeList);
        // generatedDAOImpl(typeList);
      }
    }

  }

  /**
   * 生成DAO 接口的实现类
   * 
   * @param typeList
   */
  public static void generatedDAOImpl(List<SimpleJavaType> typeList) {
    SimpleJavaType javaType = typeList.get(0);
    String content = GeneratedDaoImplInterface.buildDAOInterfaceImpl(
        new GeneratedDaoInterfaceImplEntity(typeList, DAO_IMPL_PACK, DAO_PACK));
    FileUtils.writeFile(buildDAOImplFilePath(javaType), content);
    System.out.println("generated dao implements :" + buildModelFilePath(javaType));
  }

  /**
   * 生成DAO 接口
   * 
   * @param typeList
   */
  public static void generatedDAO(List<SimpleJavaType> typeList) {
    SimpleJavaType javaType = typeList.get(0);
    String content = GeneratedDaoInterface
        .buildDAOInterface(new GeneratedDaoInterfaceEntity(typeList, DAO_PACK));
    FileUtils.writeFile(buildDAOFilePath(javaType), content);
    System.out.println("generated dao :" + buildModelFilePath(javaType));
  }

  /**
   * 生成基本的POJO类
   * 
   * @param typeList
   */
  public static void generatedPOJO(List<SimpleJavaType> typeList) {
    SimpleJavaType javaType = typeList.get(0);
    String content = new SimpleGeneratedBean().buildJavaBean(typeList, POJO_PACK);
    FileUtils.writeFile(buildModelFilePath(javaType), content);
    System.out.println("generated simple POJO :" + buildModelFilePath(javaType));
  }

  public static void generatedMapper(List<SimpleJavaType> typeList) {
    for (int i = 0; i < typeList.size(); i++) {
      SimpleJavaType javaType = typeList.get(i);
      javaType.setPackageInfo(POJO_PACK);
      StringBuilder builder = new StringBuilder(1024);
      String fileName = GeneratedMybatis.toMapper(javaType, DAO_PACK, builder);
      FileUtils.writeFile(HOME + "/mapper/" + fileName, builder.toString());
    }
  }

  public static void generatedMybatisXml(List<SimpleJavaType> typeList) {
    SimpleJavaType javaType = typeList.get(0);
    javaType.setPackageInfo(POJO_PACK);
    StringBuilder builder = new StringBuilder(1024);
    String fileName = GeneratedMybatis.toXml(typeList, POJO_PACK, DAO_PACK, builder);
    System.out.println(fileName);
    FileUtils.writeFile(HOME + "/xml/" + fileName, builder.toString());


  }

  /**
   * 生成Hibernate POJO类
   * 
   * @param typeList
   */
  public static void generateHibernatePOJO(List<SimpleJavaType> typeList) {
    SimpleJavaType javaType = typeList.get(0);
    String content = new GeneratedHibernatePojo().buildJavaBean(typeList, POJO_PACK);
    FileUtils.writeFile(buildModelFilePath(javaType), content);
    System.out.println("generated Hibernate POJO :" + buildModelFilePath(javaType));
  }

  public String buildJavaBean(Connection connection, String tableName, String pack)
      throws SQLException, NotFountExcetion {
    List<JavaBeanEntity> list = dbHander.readDBTypeToJavaType(connection, tableName);
    return new GeneratedJavaBean().buildJavaBean(list, pack);
  }

  private static String buildModelFilePath(SimpleJavaType javaType) {
    return HOME + "/model/" + javaType.getClassName() + ".java";
  }

  private static String buildDAOFilePath(SimpleJavaType javaType) {
    return HOME + "/dao/" + javaType.getDAOClassName() + ".java";
  }

  private static String buildDAOImplFilePath(SimpleJavaType javaType) {
    return HOME + "/dao/impl/" + javaType.getDAOImplClassName() + ".java";
  }
}
