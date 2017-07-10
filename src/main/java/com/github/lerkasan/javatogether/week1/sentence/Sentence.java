package com.github.lerkasan.javatogether.week1.sentence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Sentence {

  @NonNull
  @Getter
  private String sentence;

  @Getter
  private List<Word> words;

  public void setSentence(@NonNull String sentence) {
    this.sentence = sentence;
    setWords();
  }

  public int getWordsAmount() {
    Objects.requireNonNull(words, "Words field can't be null.");
    return words.size();
  }

  private void setWords() {
    Objects.requireNonNull(sentence, "Sentence field can't be null.");
    words = new ArrayList<>();
    String sentenceWithoutMarks = getSentenceWithoutMarks();
    String[] plainWords = sentenceWithoutMarks.split(" ");
    for (String element : plainWords) {
      Word word = new Word(element);
      words.add(word);
    }
  }

  @NonNull
  private String getSentenceWithoutMarks() {
    String sentenceWithoutMarks = new String(sentence);
    for (String mark : Text.specialMarks) {
      sentenceWithoutMarks = sentenceWithoutMarks.replace(mark, "");
    }
    return sentenceWithoutMarks;
  }
}
