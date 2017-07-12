package com.github.lerkasan.javatogether.week1.sentence;

import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.SENTENCE_EMPTY;
import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.SENTENCE_NON_NULL;
import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.UNIQUE_WORD_NON_NULL;
import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.WORD_NON_NULL;
import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.logger;
import static com.github.lerkasan.javatogether.week1.sentence.TextFile.SPECIAL_MARKS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@EqualsAndHashCode
public class Sentence {

  @NonNull
  @Getter
  private String sentence;

  private List<Word> allWords;

  private Set<Word> uniqueWords;

  public Sentence(@NonNull String sentence) {
    setSentence(sentence);
  }

  public void setSentence(@NonNull String sentence) {
    this.sentence = sentence;
    allWords = Collections.unmodifiableList(splitWords());
    uniqueWords = Collections.unmodifiableSet(new HashSet<>(allWords));
    getSentenceWithoutMarks();
  }

  public int getSentenceLength() {
    Objects.requireNonNull(sentence, SENTENCE_NON_NULL);
    return sentence.length();
  }

  @NonNull
  public List<Word> getAllWords() {
    return new ArrayList<>(allWords);
  }

  @NonNull
  public Set<Word> getUniqueWords() {
    return new HashSet<>(uniqueWords);
  }

  public int getAllWordsAmount() {
    Objects.requireNonNull(allWords, WORD_NON_NULL);
    return allWords.size();
  }

  public int getUniqueWordsAmount() {
    Objects.requireNonNull(uniqueWords, UNIQUE_WORD_NON_NULL);
    return uniqueWords.size();
  }

  @NonNull
  public Word getFirstLongestWord() {
    Objects.requireNonNull(uniqueWords, UNIQUE_WORD_NON_NULL);
    Word longestWord = new Word("");
    if (uniqueWords.isEmpty()) {
      logger.warn(SENTENCE_EMPTY, this);
    } else {
      longestWord = uniqueWords.iterator().next();
      for (Word word : uniqueWords) {
        if (word.isMeaningfullWord() && word.compareTo(longestWord) > 0) {
          longestWord = word;
        }
      }
    }
    return longestWord;
  }

  @NonNull
  public Word getLastShortestWord() {
    Objects.requireNonNull(uniqueWords, UNIQUE_WORD_NON_NULL);
    Word shortestWord = new Word(sentence);
    if (uniqueWords.isEmpty()) {
      logger.warn(SENTENCE_EMPTY, this);
    } else {
      shortestWord = uniqueWords.iterator().next();
      for (Word word : uniqueWords) {
        if (word.isMeaningfullWord() && word.compareTo(shortestWord) <= 0) {
          shortestWord = word;
        }
      }
    }
    return shortestWord;
  }

  @NonNull
  private List<Word> splitWords() {
    Objects.requireNonNull(sentence, SENTENCE_NON_NULL);
    List words = new ArrayList<>();
    String sentenceWithoutMarks = getSentenceWithoutMarks();
    String[] plainWords = sentenceWithoutMarks.split(" ");
    for (String element : plainWords) {
      if (!element.isEmpty()) {
        Word word = new Word(element.trim());
        words.add(word);
      }
    }
    return words;
  }

  @NonNull
  private String getSentenceWithoutMarks() {
    String sentenceWithoutMarks = sentence.toLowerCase();
    sentenceWithoutMarks = sentenceWithoutMarks.replaceAll(SPECIAL_MARKS, "");
    return sentenceWithoutMarks;
  }

  @Override
  public String toString() {
    return sentence;
  }
}
