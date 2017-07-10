package com.github.lerkasan.javatogether.week1.sentence;

import com.sun.istack.internal.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Text {

  static final String[] specialMarks = {".", ",", "!", "?", ":", ";", "\"", "(", ")", "\'", "- ", "...", "[", "]", "{", "}"};
  private static final String sentenceEndMarksRegexp = "[!.?]";

  @Getter
  private String text;

  @NotNull
  @Getter
  private String path;

  @Getter
  private List<Sentence> sentences;

  public Text(@NotNull String path) {
    this.path = path;
    readFile();
    setSentences();
  }

  @NotNull
  private String readFile() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(path));
      text = String.join(" ", lines);
    } catch (IOException e) {
      Main.logger.error("Can't read the file.", e);
    }
    return text;
  }

  private void setSentences() {
    Objects.requireNonNull(text, "Text field can't be null.");
    sentences = new ArrayList<>();
    String[] plainSentences = text.split(sentenceEndMarksRegexp);
    for (String element : plainSentences) {
      Sentence sentence = new Sentence(element);
      sentences.add(sentence);
    }
  }

  public int getSentencesAmount() {
    Objects.requireNonNull(text, "Sentences field can't be null.");
    return sentences.size();
  }

  public int getTextLength() {
    Objects.requireNonNull(text, "Text field can't be null.");
    return text.length();
  }

  public int getWordsAmount() {
    Objects.requireNonNull(text, "Sentences field can't be null.");
    int wordsAmountInText = 0;
    for (Sentence sentence : sentences) {
      wordsAmountInText += sentence.getWordsAmount();
    }
    return wordsAmountInText;
  }

  //TODO get the longest and the shortest word
  //TODO find polindromes
}
