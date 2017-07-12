package com.github.lerkasan.javatogether.week1.sentence;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) {
    for (String arg : args) {
      System.out.println("\nFile analysed: " + arg);
      TextFile text = new TextFile(arg);
      IOUtils.printTextProperties(text);
    }
    TextFile text = null;
    try {
      Path exampleFilepath = Paths.get(ClassLoader.getSystemResource("texts/minrep.txt").toURI());
      System.out.println("\nFile analysed: " + exampleFilepath);
      text = new TextFile(exampleFilepath.toString());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    IOUtils.printTextProperties(text);
  }
}
