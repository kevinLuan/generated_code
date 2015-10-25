package com.generated.code;

import java.util.List;

import com.generated.code.entity.JavaBeanBase;
import com.generated.code.entity.JavaBeanEntity;

/**
 * 生成JavaBean
 * 
 * @Description
 * @author kevin LUAN
 * @Date 2011-11-20
 */
public class GeneratedJavaBean extends BuildJavaClass {
  /**
   * 生成Java类
   * 
   * @param list
   */
  public String buildJavaBean(List<JavaBeanEntity> list, String packageInfo) {
    if (list == null || list.size() == 0)
      return "";

    StringBuilder sBuilder = new StringBuilder(200);
    JavaBeanEntity beanEntity = list.get(0);
    beanEntity.setPackageInfo(packageInfo);
    // 生成package info......
    sBuilder.append(beanEntity.getPackageInfo()).append("\n");
    buildImportPackage(list, sBuilder);
    sBuilder.append("public class " + beanEntity.getClassName() + " implements java.io.Serializable{\n");
    buildField$Getter$SetterMethod(list, sBuilder);
    buildConstructor(list, sBuilder);
    sBuilder.append("}");
    return (sBuilder.toString());
  }

  /**
   * build field and getter,setter method
   * 
   * @param sBuilder
   */
  private void buildField$Getter$SetterMethod(List<JavaBeanEntity> list, StringBuilder sBuilder) {
    for (JavaBeanEntity entity : list) {
      sBuilder.append("private " + entity.getFiledType() + " " + entity.getFieldName() + ";\n");
    }
    for (JavaBeanEntity entity : list) {
      sBuilder.append("public void " + entity.getSetterMethodName() + "(" + entity.getFiledType() + " "
          + entity.getFieldName() + "){\n" + "this." + entity.getFieldName() + "=" + entity.getFieldName() + ";\n"
          + "}\n");
      sBuilder.append("public " + entity.getFiledType() + " " + entity.getGetterMethodName() + "(){\n" + "return this."
          + entity.getFieldName() + ";\n}\n");
    }
  }

  /**
   * 生成import 包信息
   * 
   * @param list
   * @param sBuilder
   */
  private void buildImportPackage(List<JavaBeanEntity> list, StringBuilder sBuilder) {
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

    for (JavaBeanEntity entity : list) {
      if (entity.getImportPackageName() != null && entity.getImportPackageName().indexOf("java.lang.") == -1) {
        sBuilder.append("import " + entity.getImportPackageName() + ";\n");
      }
    }
  }

  /**
   * 生成构造函数
   * 
   * @return
   */
  private void buildConstructor(List<JavaBeanEntity> list, StringBuilder sBuilder) {
    if (list != null && list.size() > 0) {
      JavaBeanBase beanBase = list.get(0);
      sBuilder.append("public " + beanBase.getClassName() + "(){\n}\n");// 生成构造方法

      sBuilder.append("public " + beanBase.getClassName() + "(");
      for (int i = 0; i < list.size(); i++) {
        JavaBeanEntity entity = list.get(i);
        if (i == list.size() - 1) {
          sBuilder.append(entity.getFiledType() + " " + entity.getFieldName());
        } else {
          sBuilder.append(entity.getFiledType() + " " + entity.getFieldName()).append(",");
        }
      }
      sBuilder.append("){\n");
      for (JavaBeanEntity entity : list) {
        sBuilder.append("this." + entity.getFieldName() + "=" + entity.getFieldName() + ";\n");
      }
      sBuilder.append("}\n");
    }
  }
}
