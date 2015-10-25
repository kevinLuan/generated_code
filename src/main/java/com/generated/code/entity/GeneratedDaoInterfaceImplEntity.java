package com.generated.code.entity;

import java.util.ArrayList;
import java.util.List;

public class GeneratedDaoInterfaceImplEntity extends JavaBeanBase {
  private List<String> importList = new ArrayList<String>();

  public void addImport(String pack) {
    if (pack.startsWith("import ") && pack.endsWith(";")) {
      importList.add(pack.trim());
    } else if (pack.startsWith("import ")) {
      importList.add(pack + ";");
    } else if (pack.endsWith(";")) {
      importList.add("import " + pack.trim() + "");
    } else {
      importList.add("import " + pack.trim() + ";");
    }
  }

  public void buildProperties(StringBuilder builder) {
    builder.append("@Autowired\n");
    builder.append("@Qualifier(\"" + simpleJavaType.getDatabaseName().toLowerCase() + "\")\n");
    builder.append("private HibernateTemplate template;\n");
  }

  public void buildCountAllMethod(StringBuilder builder) {
    builder.append("public int count(){\n");
    builder.append("\tString hql=\"select " + simpleJavaType.getFieldName() + " from " + simpleJavaType.getClassName()
        + "\";\n");
    builder.append("\treturn " + buildDBOperInstance() + ".count(hql);\n");
    builder.append("}\n");
  }

  public void buildDeleteByFristFieldMethod(StringBuilder builder) {
    addImport(simpleJavaType.getImportPackageName());
    builder.append("public boolean delete(" + simpleJavaType.getFieldType() + " " + simpleJavaType.getFieldName()
        + "){\n");
    builder.append("\tString sql = \"DELETE FROM " + simpleJavaType.getTableName() + " WHERE "
        + simpleJavaType.getColumnName() + "=?\";\n");
    builder.append("\treturn " + buildDBOperInstance() + ".updateBySQL(sql) > 0;\n");
    builder.append("}\n");
  }

  public void buildDeleteMethod(StringBuilder builder) {
    builder.append("public void delete(" + simpleJavaType.getClassName() + " entity){\n");
    builder.append("\tthis.template.delete(entity);\n");
    builder.append("}\n");
  }

  public void buildFindAllMethod(StringBuilder builder) {
    addImport("java.util.List");
    builder.append("public List<" + simpleJavaType.getFieldType() + "> find(int startIndex,int pageSize){\n");
    builder.append("\tfinal String hql =\"select " + simpleJavaType.getFieldName() + " from "
        + simpleJavaType.getClassName() + "\";\n");
    builder.append("\treturn " + buildDBOperInstance() + ".setPage(startIndex,pageSize).executeQueryByHQL(hql);\n");
    builder.append("}\n");
  }

  public void buildFindByFirstFieldMethod(StringBuilder builder) {
    addImport(simpleJavaType.getImportPackageName());
    builder.append("public " + simpleJavaType.getClassName() + " get(" + simpleJavaType.getFieldType() + " "
        + simpleJavaType.getFieldName() + "){\n");
    builder.append("\tfinal String hql = \"from " + simpleJavaType.getClassName() + " WHERE "
        + simpleJavaType.getFieldName() + "=? \";\n");
    builder.append("\treturn (" + simpleJavaType.getClassName() + ")" + buildDBOperInstance()
        + ".executeScalarByHQL(hql," + simpleJavaType.getFieldName() + ");\n");
    builder.append("}\n");
  }

  public void buildSaveMethod(StringBuilder builder) {
    addImport(simpleJavaType.getImportPackageName());
    builder.append("public void save(" + simpleJavaType.getClassName() + " entity){\n");
    builder.append("\tthis.template.save(entity);\n");
    builder.append("}\n");
  }

  public void buildUpdateMethod(StringBuilder builder) {
    builder.append("public void update(" + simpleJavaType.getClassName() + " entity){\n");
    builder.append("\tthis.template.update(entity);\n");
    builder.append("}\n");
  }

  public List<SimpleJavaType> simpleTypeList;
  public SimpleJavaType simpleJavaType;

  public GeneratedDaoInterfaceImplEntity(List<SimpleJavaType> simpleTypeList, String packageInfo, String daoPackage) {
    this.simpleTypeList = simpleTypeList;
    super.setPackageInfo(packageInfo);
    if (simpleTypeList != null && simpleTypeList.size() > 0) {
      simpleJavaType = simpleTypeList.get(0);
      String entityPackinfo = simpleJavaType.getPackageInfo();
      if (entityPackinfo.startsWith("package")) {
        entityPackinfo = entityPackinfo.substring("package".length());
      }
      if (entityPackinfo.endsWith(";")) {
        entityPackinfo = entityPackinfo.substring(0, entityPackinfo.length() - 1);
      }

      addImport(entityPackinfo + "." + simpleJavaType.getClassName() + ";");
    }
    addImport("org.springframework.beans.factory.annotation.Autowired");
    addImport("org.springframework.beans.factory.annotation.Qualifier");
    addImport("org.springframework.orm.hibernate3.HibernateTemplate");
    addImport("org.springframework.stereotype.Component");
    addImport("com.feinno.wap.game.dbHelper.CommonDBOperate");
    addImport(daoPackage + "." + simpleJavaType.getDAOClassName());
  }

  @Override
  public String getClassName() {
    return simpleJavaType.getDAOImplClassName();
  }

  public List<String> getImportList() {
    for (int i = 0; i < importList.size(); i++) {
      if (importList.get(i).startsWith("import java.lang.")) {
        importList.remove(i);
        i--;
        continue;
      }
      for (int j = i + 1; j < importList.size(); j++) {

        if (importList.get(i).equalsIgnoreCase(importList.get(j))) {
          importList.remove(j);
          j--;
          break;
        }
      }
    }
    return importList;
  }

  public List<SimpleJavaType> getSimpTypeList() {
    return simpleTypeList;
  }

  public void setSimpTypeList(List<SimpleJavaType> simpleTypeList) {
    this.simpleTypeList = simpleTypeList;
  }

  public String buildDBOperInstance() {
    return "CommonDBOperate.createInstance(template)";
  }
}
