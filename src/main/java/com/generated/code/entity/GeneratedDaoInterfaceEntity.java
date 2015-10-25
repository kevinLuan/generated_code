package com.generated.code.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成DAO接口
 * 
 * @author kevin LUAN
 * @email kevin_Luan@126.com
 * @Date 2011-11-25
 */
public class GeneratedDaoInterfaceEntity extends JavaBeanBase {
  public List<SimpleJavaType> simpTypeList;
  public SimpleJavaType simpleJavaType;
  public List<String> importList = new ArrayList<String>();

  public GeneratedDaoInterfaceEntity(List<SimpleJavaType> simpTypeList, String packageInfo) {
    this.simpTypeList = simpTypeList;
    super.setPackageInfo(packageInfo);
    if (simpTypeList != null && simpTypeList.size() > 0) {
      simpleJavaType = simpTypeList.get(0);
      String entityPackinfo = simpleJavaType.getPackageInfo();
      if (entityPackinfo.startsWith("package")) {
        entityPackinfo = entityPackinfo.substring("package".length());
      }
      if (entityPackinfo.endsWith(";")) {
        entityPackinfo = entityPackinfo.substring(0, entityPackinfo.length() - 1);
      }
      entityPackinfo = "import" + entityPackinfo + "." + simpleJavaType.getClassName() + ";";
      importList.add(entityPackinfo);
    }
  }

  public String buildCountAllMethod() {
    return "public int count();";
  }

  public String buildFindAllMethod() {
    importList.add("import java.util.List;");
    return "public List<" + simpleJavaType.getFieldType() + "> find(int startIndex,int pageSize);";
  }

  public String buildFindByFirstFieldMethod() {
    importList.add("import " + simpleJavaType.getImportPackageName());
    return "public " + simpleJavaType.getClassName() + " get(" + simpleJavaType.getFieldType() + " "
        + simpleJavaType.getFieldName() + ");";
  }

  /**
   * 使用第一个字段作为删除参数
   * 
   * @return
   */
  public String buildDeleteByFristFieldMethod() {
    importList.add("import " + simpleJavaType.getImportPackageName());
    return "public boolean delete(" + simpleJavaType.getFieldType() + " " + simpleJavaType.getFieldName() + ");";
  }

  public String buildSaveMethod() {
    return "public void save(" + simpleJavaType.getClassName() + " entity);";
  }

  public String buildDeleteMethod() {
    return "public void delete(" + simpleJavaType.getClassName() + " entity);";
  }

  public String buildUpdateMethod() {
    return "public void update(" + simpleJavaType.getClassName() + " entity);";
  }

  public List<SimpleJavaType> getSimpTypeList() {
    return simpTypeList;
  }

  public void setSimpTypeList(List<SimpleJavaType> simpTypeList) {
    this.simpTypeList = simpTypeList;
  }

  public List<String> getImportList() {
    return importList;
  }

  public void setImportList(List<String> importList) {
    this.importList = importList;
  }

  @Override
  public String getClassName() {
    return simpleJavaType.getDAOClassName();
  }

  public SimpleJavaType getSimpleJavaType() {
    return simpleJavaType;
  }

  public void setSimpleJavaType(SimpleJavaType simpleJavaType) {
    this.simpleJavaType = simpleJavaType;
  }
}
