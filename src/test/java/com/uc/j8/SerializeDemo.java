package com.uc.j8;

import com.uc.renren.bean.TestEntry;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author yangxinyan
 * @date 2019/5/17 13:09
 */
public class SerializeDemo {

  public static void main(String[] args) {

    TestEntry entry = new TestEntry();
    entry.setId(123);
    entry.setText("哈哈 李白 love");
    String filePath = "/Users/yangxinyan/logs/employee.ser";
    try {
      FileOutputStream fileOut =
          new FileOutputStream(filePath);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(entry);
      out.close();
      fileOut.close();
      System.out.printf(filePath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }
}
