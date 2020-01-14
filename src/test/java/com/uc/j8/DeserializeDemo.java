package com.uc.j8;

import com.uc.renren.bean.TestEntry;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeDemo {

  public static void main(String[] args) {
    TestEntry testEntry = null;
    String filePath = "/Users/yangxinyan/logs/employee.ser";

    try {
      FileInputStream fileIn = new FileInputStream(filePath);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      testEntry = (TestEntry) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("Employee class not found");
      c.printStackTrace();
      return;
    }
    System.out.println("Deserialized Employee...");
    System.out.println(testEntry.getId());
    System.out.println(testEntry.getText());
  }
}