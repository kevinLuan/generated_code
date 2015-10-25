package com.generated.code.util;

public class StringUtils {

  /**
   * 将字符串拆分，将首字母和下划线的下一个字母改为大写字母并且去掉下划线
   * 
   * @param str
   * @return
   */
  public static String processStr_(String str) {
    if (str.indexOf("_") != -1) {
      String fieldStrs[] = str.split("_");
      if (fieldStrs != null) {
        for (int i = 0; i < fieldStrs.length; i++) {
          if (fieldStrs[i] != null && fieldStrs[i].length() > 0) {
            if (is_a_z(fieldStrs[i].charAt(0))) {
              fieldStrs[i] = ((char) (fieldStrs[i].charAt(0) - 32)) + fieldStrs[i].substring(1);
            }
          }
        }
      }
      str = "";
      for (int i = 0; i < fieldStrs.length; i++) {
        str += fieldStrs[i];
      }
    } else {
      if (is_a_z(str.charAt(0))) {
        return ((char) (str.charAt(0) - 32)) + str.substring(1);
      }
    }
    return str;
  }

  public static char a_zToA_Z(char chat) {
    if (is_a_z(chat)) {
      return ((char) (chat - 32));
    }
    return chat;
  }

  public static char A_ZToa_z(char chat) {
    if (isA_Z(chat)) {
      return ((char) (chat + 32));
    }
    return chat;
  }

  public static boolean is_a_z(char chat) {
    if (chat >= 97 && chat <= 122) {
      return true;
    }
    return false;
  }

  public static boolean isA_Z(char chat) {
    if (chat >= 65 && chat <= 90) {
      return true;
    }
    return false;
  }
}
