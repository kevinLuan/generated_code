package com.generated.code;

import java.sql.Types;
import java.util.List;

import com.generated.code.entity.SimpleJavaType;

public class GeneratedHibernatePojo extends SimpleGeneratedBean {
  @Override
  public void buildImportPackage(List<SimpleJavaType> list, StringBuilder builder) {
    super.buildImportPackage(list, builder);
    builder.append("import javax.persistence.Column;\n");
    builder.append("import javax.persistence.Entity;\n");
    builder.append("import javax.persistence.Id;\n");
    builder.append("import javax.persistence.Table;\n");
    builder.append("import javax.persistence.GeneratedValue;\n");
    builder.append("import javax.persistence.GenerationType;\n");
  }

  @Override
  public void buildFieldBefore(SimpleJavaType typeJava, StringBuilder sBuilder) {
    if (typeJava.getColumnName().equalsIgnoreCase("id")
        && (typeJava.getColumnType() == Types.BIGINT || typeJava.getColumnType() == Types.INTEGER)) {
      sBuilder.append("@Id").append("\n");
      sBuilder.append("@GeneratedValue(strategy = GenerationType.AUTO)").append("\n");
    } else {
      sBuilder.append("@Column(name=\"" + typeJava.getColumnName() + "\")\n");
    }
  }

  @Override
  public void buildClassBefore(SimpleJavaType typeJava, StringBuilder sBuilder) {
    sBuilder.append("@Entity").append("\n").append("@Table(name =\"" + typeJava.getTableName() + "\")\n");
  }

}
