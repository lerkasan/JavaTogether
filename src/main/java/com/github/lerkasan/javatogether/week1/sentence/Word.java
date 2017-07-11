package com.github.lerkasan.javatogether.week1.sentence;

import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.WORD_NON_NULL;

import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Word implements Comparable<Word> {

  public static final int MIN_WORD_LENGTH = 3;

  @NonNull
  private String word;

  public int getLength() {
    Objects.requireNonNull(word, WORD_NON_NULL);
    return word.length();
  }

  public boolean isPalindrome() {
    Objects.requireNonNull(word, WORD_NON_NULL);
    if (word.length() < MIN_WORD_LENGTH) {
      return false;
    }
    StringBuilder strBuiltText = new StringBuilder(word);
    String reversedWord = strBuiltText.reverse().toString();
    return word.equals(reversedWord);
  }

  public boolean isMeaningfullWord() {
    return getLength() >= MIN_WORD_LENGTH;
  }

  @Override
  public String toString() {
    return  word;
  }

  @Override
  public int compareTo(Word other) {
    return getLength() - other.getLength();
  }
}
