package com.generated.code.dao;

import com.generated.code.entity.GeneratedDaoInterfaceEntity;

public class GeneratedDaoInterface {

  public static String buildDAOInterface(GeneratedDaoInterfaceEntity genDaoInterfaceEntiry) {
    StringBuilder builder = new StringBuilder(500);
    builder.append(genDaoInterfaceEntiry.getPackageInfo() + "\n");
    String genMethod = buildMethod(genDaoInterfaceEntiry);
    for (int i = 0; i < genDaoInterfaceEntiry.getImportList().size(); i++) {
      if (!genDaoInterfaceEntiry.getImportList().get(i).startsWith("import java.lang.")) {
        builder.append(genDaoInterfaceEntiry.getImportList().get(i) + "\n");
      }
    }
    builder.append("public interface " + genDaoInterfaceEntiry.getClassName());
    builder.append("{\n");
    builder.append(genMethod);
    builder.append("}\n");
    return (builder.toString());
  }

  public static String buildMethod(GeneratedDaoInterfaceEntity genDaoInterfaceEntiry) {
    StringBuilder builder = new StringBuilder(500);
    builder.append(genDaoInterfaceEntiry.buildCountAllMethod() + "\n");
    builder.append(genDaoInterfaceEntiry.buildDeleteByFristFieldMethod() + "\n");
    builder.append(genDaoInterfaceEntiry.buildDeleteMethod() + "\n");
    builder.append(genDaoInterfaceEntiry.buildFindAllMethod() + "\n");
    builder.append(genDaoInterfaceEntiry.buildFindByFirstFieldMethod() + "\n");
    builder.append(genDaoInterfaceEntiry.buildSaveMethod() + "\n");
    builder.append(genDaoInterfaceEntiry.buildUpdateMethod() + "\n");
    return builder.toString();
  }
}
