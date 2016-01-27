package com.generated.code;

import java.util.List;

import com.generated.code.entity.SimpleJavaType;

/**
 * 简单生成JavaBean
 * 
 * @Description
 * @author kevin LUAN
 * @Date 2011-11-20
 */
public class SimpleGeneratedBean extends BuildJavaClass {
  /**
   * 生成JavaBean
   * 
   * @param simpTypeList
   * @param packageInfo
   */
  public String buildJavaBean(List<SimpleJavaType> simpTypeList, String packageInfo) {
    if (simpTypeList == null || simpTypeList.size() == 0)
      return "";

    StringBuilder sBuilder = new StringBuilder(200);
    SimpleJavaType beanEntity = simpTypeList.get(0);
    beanEntity.setPackageInfo(packageInfo);
    // 生成package info......
    sBuilder.append(beanEntity.getPackageInfo()).append("\n");
    buildImportPackage(simpTypeList, sBuilder);
    buildClassBefore(beanEntity, sBuilder);
    sBuilder.append("public class " + beanEntity.getClassName() + " implements java.io.Serializable{\n");
    buildField(simpTypeList, sBuilder);
    buildGetterAndSetterMethod(simpTypeList, sBuilder);
    buildConstructor(simpTypeList, sBuilder);
    sBuilder.append("}");
    return sBuilder.toString();
  }

  /**
   * 在生成属性之前调用,可由自定义实现的子类实现
   * 
   * @param list
   * @param sBuilder
   */
  public void buildFieldBefore(SimpleJavaType typeJava, StringBuilder sBuilder) {}

  /**
   * 生成类名称之前调用,可由自定义实现的子类实现
   * 
   * @param typeJava
   * @param sBuilder
   */
  public void buildClassBefore(SimpleJavaType typeJava, StringBuilder sBuilder) {}

  /**
   * 生成方法前调用,可由自定义实现的子类实现
   */
  public void buildMethodBefore(SimpleJavaType entity, StringBuilder sBuilder, String method) {}

  /**
   * build field and getter,setter method
   * 
   * @param sBuilder
   */
  public void buildGetterAndSetterMethod(List<SimpleJavaType> list, StringBuilder sBuilder) {

    for (SimpleJavaType entity : list) {
      buildMethodBefore(entity, sBuilder, entity.getSetterMethodName());
      sBuilder.append("public void " + entity.getSetterMethodName() + "(" + entity.getFieldType() + " "
          + entity.getFieldName() + "){\n" + "this." + entity.getFieldName() + "=" + entity.getFieldName() + ";\n"
          + "}\n");
      buildMethodBefore(entity, sBuilder, entity.getGetterMethodName());
      sBuilder.append("public " + entity.getFieldType() + " " + entity.getGetterMethodName() + "(){\n" + "return this."
          + entity.getFieldName() + ";\n}\n");
    }
  }

  /**
   * 生成属性
   * 
   * @param list
   * @param builder
   */
  public void buildField(List<SimpleJavaType> list, StringBuilder builder) {
    for (SimpleJavaType entity : list) {
      buildFieldComment(builder, entity);
      buildFieldBefore(entity, builder);
      builder.append("private " + entity.getFieldType() + " " + entity.getFieldName() + ";\n");
    }
  }

  /**
   * 生成Field注释
   * 
   * @param builder
   * @param entity
   */
  protected void buildFieldComment(StringBuilder builder, SimpleJavaType entity) {
    if (entity.getComment() != null && entity.getComment().trim().length() > 0) {
      builder.append(
          "/**\n"
          + "* " + entity.getComment() + "\n"
          + "**/\n"
     );
    }
  }

  /**
   * 生成import 包信息
   * 
   * @param list
   * @param sBuilder
   */
  public void buildImportPackage(List<SimpleJavaType> list, StringBuilder sBuilder) {
    buildImportBefore(sBuilder);
    // 出去重复的引用包
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        if (!list.get(i).isImport())
          break;
        if (!list.get(j).isImport())
          continue;
        if (list.get(i).getImportPackageName().equalsIgnoreCase(list.get(j).getImportPackageName())) {
          list.get(j).setImport(false);
        }
      }
    }

    for (SimpleJavaType entity : list) {
      if (entity.getImportPackageName() != null && entity.getImportPackageName().indexOf("java.lang.") == -1) {
        sBuilder.append("import " + entity.getColumnClassName() + ";\n");
        // sBuilder.append("import "
        // +entity.getImportPackageName()+".*;\n");
      }
    }
  }

  public void buildImportBefore(StringBuilder sBuilder) {
    // TODO 可由各自实现的自定义子类在实现
  }

  /**
   * 可由各自实现的自定义子类在实现
   */
  public void buildConstructorBefore(SimpleJavaType typeJava, StringBuilder sBuilder) {
    // TODO 可由各自实现的自定义子类在实现
  }

  public void buildConstructorBefore(List<SimpleJavaType> list, StringBuilder sBuilder) {
    // TODO 可由各自实现的自定义子类在实现
  }

  /**
   * <p>
   * 生成构造函数
   * </p>
   * <p>
   * 生成无参数的构造函数和生成全部参数的构造函数
   * </p>
   * 
   * @return
   */
  public void buildConstructor(List<SimpleJavaType> list, StringBuilder sBuilder) {
    if (list != null && list.size() > 0) {
      SimpleJavaType beanBase = list.get(0);
      buildConstructorBefore(beanBase, sBuilder);
      sBuilder.append("public " + beanBase.getClassName() + "(){\n}\n");// 生成构造方法
      buildConstructorBefore(list, sBuilder);
      sBuilder.append("public " + beanBase.getClassName() + "(");
      for (int i = 0; i < list.size(); i++) {
        SimpleJavaType entity = list.get(i);
        if (i == list.size() - 1) {
          sBuilder.append(entity.getFieldType() + " " + entity.getFieldName());
        } else {
          sBuilder.append(entity.getFieldType() + " " + entity.getFieldName()).append(",");
        }
      }
      sBuilder.append("){\n");
      for (SimpleJavaType entity : list) {
        sBuilder.append("this." + entity.getFieldName() + "=" + entity.getFieldName() + ";\n");
      }
      sBuilder.append("}\n");
    }
  }
}
