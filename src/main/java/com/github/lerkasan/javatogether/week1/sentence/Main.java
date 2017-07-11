package com.github.lerkasan.javatogether.week1.sentence;

public class Main {

  public static void main(String[] args) {
    for (String arg : args) {
      System.out.println("\n");
      TextFile text = new TextFile(arg);
      IOUtils.printTextProperties(text);
    }
  }
}
