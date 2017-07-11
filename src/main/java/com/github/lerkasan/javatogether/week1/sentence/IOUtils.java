package com.github.lerkasan.javatogether.week1.sentence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils {

  static final String UNIQUE_WORD_NON_NULL = "UniqueWords field can't be null.";
  static final String SENTENCE_NON_NULL = "Sentence field can't be null.";
  static final String TEXT_NON_NULL = "TextFile field can't be null.";
  static final String WORD_NON_NULL = "Word field shouldn't be null.";
  static final String SENTENCE_EMPTY = "Sentence is empty, there are no words in it.";

  static final Logger logger = LoggerFactory.getLogger(Main.class);

  private IOUtils() {}

  @NonNull
  public static String readFile(@NonNull String path) {
    String text = "";
    try {
      List<String> lines = Files.readAllLines(Paths.get(path));
      text = String.join(" ", lines);
    } catch (IOException e) {
      logger.error("Can't read the file " + path.trim());
    }
    return text;
  }

  public static void printTextProperties(TextFile text) {
    System.out.println("Text is: " + text.getText());
    System.out.println("Text length is " + text.getTextLength() + " chars, " +
        text.getTextLengthWithoutSpaces() + " chars without spaces");
    System.out.println("Words amount is: " + text.getAllWordsAmount());
    System.out.println("Sentences amount is: " + text.getSentencesAmount());
    if (text.getSentencesAmount() > 0) {
      int counter = 1;
      for (Sentence sentence : text.getSentences()) {
        System.out.println(
            "Words amount in sentence #" + counter + " is: " + sentence.getAllWordsAmount());
        counter++;
      }
    }
    System.out.println("The longest word length is: " + text.calculateLongestWordLength());
    System.out.print("The longest words are: ");
    for (Word longWord : text.getLongestWords()) {
      System.out.print(longWord + " ");
    }
    System.out.println("\nThe shortest word length is: " + text.getShortestWordLength());
    System.out.print("The shortest words are: ");
    for (Word shortWord : text.getShortestWords()) {
      System.out.print(shortWord + " ");
    }
    System.out.print("\nText has " + text.getPolindromesAmount() + " polindromes: ");
    for (Word polindrome : text.getPolindromes()) {
      System.out.print(polindrome + " ");
    }
  }
}
