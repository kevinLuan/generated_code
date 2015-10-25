package com.generated.code.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Reader Writer File
 * 
 * @author kevin Luan
 * @email kevin_Luan@126.com
 */
public class FileUtils {
  private static final SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static String getTimeFormat(Date aDate) {
    if (aDate == null || mFormat == null) {
      return "";
    }
    return mFormat.format(aDate);
  }

  public final static String EnterLine = "\r\n";
  public final static String Enter = "\r";
  public final static String NewLine = "\n";

  /**
   * 先使用流的方式读取。处理可能出的中文乱码问题
   * 
   * @param file
   * @param readEncoding
   * @param destEncoding
   * @return
   */
  public static List<String> readFileToList(File file, String readEncoding, String destEncoding) {
    List<String> list = new ArrayList<String>();
    InputStreamReader fReader = null;
    FileInputStream input = null;
    BufferedReader bReader = null;
    try {
      input = new FileInputStream(file);
      fReader = new InputStreamReader(input, readEncoding);
      bReader = new BufferedReader(fReader);
      while (bReader.ready()) {
        list.add(new String(bReader.readLine().getBytes(readEncoding), destEncoding));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fReader != null)
          fReader.close();
        if (bReader != null)
          bReader.close();
        if (file != null)
          file = null;
      } catch (IOException e) {
      }
      fReader = null;
      bReader = null;
    }
    return list;
  }

  public static List<String> readFileToList(String path, String readEncoding, String destEncoding) {
    List<String> list = new ArrayList<String>();
    FileReader fReader = null;
    BufferedReader bReader = null;
    try {
      fReader = new FileReader(path);
      bReader = new BufferedReader(fReader);
      while (bReader.ready()) {
        list.add(new String(bReader.readLine().getBytes(readEncoding), destEncoding));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fReader != null)
          fReader.close();
        if (bReader != null)
          bReader.close();
      } catch (IOException e) {
      }
      fReader = null;
      bReader = null;
    }
    return list;
  }

  public static String readFileToString(String path, String readEncoding, String destEncoding) {
    StringBuffer sBuffer = new StringBuffer(200);
    FileReader fReader = null;
    BufferedReader bReader = null;
    try {
      fReader = new FileReader(path);
      bReader = new BufferedReader(fReader);
      while (bReader.ready()) {
        sBuffer.append(new String(bReader.readLine().getBytes(readEncoding), destEncoding));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fReader != null)
          fReader.close();
        if (bReader != null)
          bReader.close();
      } catch (IOException e) {
      }
      fReader = null;
      bReader = null;
    }
    return sBuffer.toString();
  }

  /**
   * 读取文件内容
   * 
   * @param Buffer
   */
  public static String readerFile(String filePath) {
    FileReader fr = null;
    BufferedReader br = null;
    StringBuffer sBuffer = new StringBuffer();
    try {
      fr = new FileReader(filePath);
      br = new BufferedReader(fr);
      String line = br.readLine();
      while (line != null) {
        sBuffer.append(line = br.readLine());
      }
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (br != null)
          br.close();
        if (fr != null)
          fr.close();
      } catch (IOException e) {
      } finally {
        br = null;
        fr = null;
      }
    }
    return sBuffer.toString();
  }

  /**
   * 写入文件
   * 
   * @param filePath
   * @param content 内容
   * @param isAppend 是否追加到文本
   */
  public static void writerFile(String filePath, String content, boolean isAppend) {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
      }
      // 每次写入使用追加的方式写入
      FileWriter fw = new FileWriter(file, isAppend);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(content);
      bw.newLine();
      bw.close();
      fw.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void writeFile(String filePath, String content) {
    FileWriter fw = null;
    BufferedWriter bw = null;
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
      }
      fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      bw.write(content);
      bw.newLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (bw != null)
          bw.close();
        if (fw != null)
          fw.close();
      } catch (IOException e) {
      } finally {
        bw = null;
        fw = null;
      }
    }
  }

  /**
   * 写入文件
   * 
   * @param Buffer
   */
  public static void printWriter(File file, String content) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(file);
      pw.println(content);
      pw.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (pw != null)
        pw.close();
      pw = null;
      file = null;
    }
  }

  /**
   * 使用套接字方式写入文件
   * 
   * @param filePath
   * @param content
   * @param isAppend 是否追加
   */
  public void printWriter(String filePath, String content, boolean isAppend) {
    PrintWriter pw = null;
    try {
      if (isAppend) {
        // 每次写入使用追加的方式写入
        pw = new PrintWriter(new FileOutputStream(filePath, true), true);
      } else {// 每次从新开始新的文档
        pw = new PrintWriter(new FileOutputStream(filePath), true);
      }
      pw.println(content);
      pw.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (pw != null)
        pw.close();
      pw = null;
    }
  }

  public void createFile(File file) {
    try {
      if (!file.exists()) {
        File dir = new File(file.getParent());
        if (!dir.exists()) {
          dir.mkdir();
        }
        file.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
