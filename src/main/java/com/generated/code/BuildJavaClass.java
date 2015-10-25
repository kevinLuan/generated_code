package com.generated.code;

import java.net.URL;

class BuildJavaClass {
  /**
   * 获取当前类路径
   * 
   * @return
   */
  public String getCurrentClassPath() {
    URL url = getClass().getResource("/");
    return url.getPath() + this.getClass().getPackage().getName().replaceAll("\\.", "/");
  }
}
