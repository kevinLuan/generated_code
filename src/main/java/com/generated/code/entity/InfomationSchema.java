package com.generated.code.entity;

/**
 * MYSQL DB基础数据
 * 
 * @Description
 * @author kevin LUAN
 * @email kevin_Luan@126.com
 * @Date 2011-11-27
 */
public class InfomationSchema {
  public String TABLE_NAME;// 表名称
  public String COLUMN_NAME;// 列名称
  public String COLUMN_DEFAULT;// 列默认值
  public String IS_NULLABLE;// 是否可以为空
  public String DATA_TYPE;// 数据类型
  public String CHARACTER_MAXIMUM_LENGTH;// 列最大长度
  public String COLUMN_KEY;// 列Key：主键:PRI 外键：MUL
  public String COLUMN_COMMENT;// 列注释

  public enum NULLABLE_TYPE {
    YES("可以为空"), NO("不能为空");
    public String description;

    private NULLABLE_TYPE(String description) {
      this.description = description;
    }
  }

  public enum KEY_TYPE {
    PRI("主键"), MUL("外键"), NULL("空");
    public String description;

    private KEY_TYPE(String description) {
      this.description = description;
    }
  }

}
