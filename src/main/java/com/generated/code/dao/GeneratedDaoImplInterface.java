package com.generated.code.dao;

import java.util.List;

import com.generated.code.entity.GeneratedDaoInterfaceImplEntity;

public class GeneratedDaoImplInterface {
  public static String buildDAOInterfaceImpl(GeneratedDaoInterfaceImplEntity genDaoInterfaceImplEntiry) {
    StringBuilder builder = new StringBuilder(1000);
    builder.append(genDaoInterfaceImplEntiry.getPackageInfo() + "\n");
    String genMethod = buildMethod(genDaoInterfaceImplEntiry);
    genDaoInterfaceImplEntiry.addImport("org.springframework.stereotype.Component");
    List<String> importList = genDaoInterfaceImplEntiry.getImportList();
    for (String importInfo : importList) {
      builder.append(importInfo + "\n");
    }
    builder.append("@Component\n");
    builder.append("public class " + genDaoInterfaceImplEntiry.getClassName() + " implements "
        + genDaoInterfaceImplEntiry.simpleJavaType.getDAOClassName());
    builder.append("{\n");
    genDaoInterfaceImplEntiry.buildProperties(builder);
    builder.append(genMethod);
    builder.append("}\n");
    return (builder.toString());
  }

  public static String buildMethod(GeneratedDaoInterfaceImplEntity genDaoInterfaceImplEntiry) {
    StringBuilder builder = new StringBuilder(1000);
    genDaoInterfaceImplEntiry.buildCountAllMethod(builder);
    genDaoInterfaceImplEntiry.buildDeleteByFristFieldMethod(builder);
    genDaoInterfaceImplEntiry.buildDeleteMethod(builder);
    genDaoInterfaceImplEntiry.buildFindAllMethod(builder);
    genDaoInterfaceImplEntiry.buildFindByFirstFieldMethod(builder);
    genDaoInterfaceImplEntiry.buildSaveMethod(builder);
    genDaoInterfaceImplEntiry.buildUpdateMethod(builder);
    return builder.toString();
  }
}
