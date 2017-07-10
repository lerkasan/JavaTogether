package com.github.lerkasan.javatogether.week1.sentence;

import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Word {

  @NonNull
  private String word;

  public int getLength() {
    Objects.requireNonNull(word, "Word field shouldn't be null.");
    return word.length();
  }

  public boolean isPalindrome() {
    Objects.requireNonNull(word, "Word field shouldn't be null.");
    StringBuilder strBuiltText = new StringBuilder(word);
    String reversedText = strBuiltText.reverse().toString();
    return word.equals(reversedText);
  }
}
