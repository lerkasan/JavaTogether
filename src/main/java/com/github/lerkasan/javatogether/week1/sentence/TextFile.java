package com.github.lerkasan.javatogether.week1.sentence;

import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.SENTENCE_NON_NULL;
import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.TEXT_NON_NULL;
import static com.github.lerkasan.javatogether.week1.sentence.IOUtils.logger;

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
public class TextFile {

  static final String SPECIAL_MARKS = "[\\.,!?:;\"()\']| - |- | -|\\.\\.\\.|[\\[\\]\\{\\}]";
  private static final String SENTENCE_END_MARKS_REGEXP = "! |\\. |\\.|\\? |\\.\\.\\.";

  @Getter
  private String text;

  @NonNull
  @Getter
  private String path;

  @Getter
  private List<Sentence> sentences;

  private int longestWordLength;

  public TextFile(@NonNull String path) {
    this.path = path;
    text = IOUtils.readFile(path);
    sentences = Collections.unmodifiableList(splitSentences());
  }

  @NonNull
  private List<Sentence> splitSentences() {
    Objects.requireNonNull(text, TEXT_NON_NULL);
    List<Sentence> sentences = new ArrayList<>();
    if (text.isEmpty()) {
      logger.warn("File " + path + " is empty or doesn't exist.");
      return new ArrayList<>(sentences);
    }
    String[] plainSentences = text.split(SENTENCE_END_MARKS_REGEXP);
    for (String element : plainSentences) {
      Sentence sentence = new Sentence(element.trim());
      sentences.add(sentence);
    }
    return new ArrayList<>(sentences);
  }

  public int getSentencesAmount() {
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    return sentences.size();
  }

  public int getTextLength() {
    Objects.requireNonNull(text, TEXT_NON_NULL);
    return text.length();
  }

  public int getTextLengthWithoutSpaces() {
    Objects.requireNonNull(text, TEXT_NON_NULL);
    if (getAllWordsAmount() <= 1 ) {
      return text.length();
    }
    return text.length() - getAllWordsAmount() + 1;
  }

  @NonNull
  public List<Word> getAllWords() {
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    List<Word> allWords = new ArrayList<>();
    for (Sentence sentence : sentences) {
      allWords.addAll(sentence.getAllWords());
    }
    return allWords;
  }

  @NonNull
  public Set<Word> getUniqueWords() {
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    Set<Word> uniqueWords = new HashSet<>();
    for (Sentence sentence : sentences) {
      uniqueWords.addAll(sentence.getUniqueWords());
    }
    return uniqueWords;
  }

  public int getAllWordsAmount() {
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    int allWordsAmountInText = 0;
    for (Sentence sentence : sentences) {
      allWordsAmountInText += sentence.getAllWordsAmount();
    }
    return allWordsAmountInText;
  }

  public int getUniqueWordsAmount() {
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    int uniqueWordsAmountInText = 0;
    for (Sentence sentence : sentences) {
      uniqueWordsAmountInText += sentence.getUniqueWordsAmount();
    }
    return uniqueWordsAmountInText;
  }

  @NonNull
  public List<Word> getPolindromes() {
    List<Word> polindromes = new ArrayList<>();
    Set<Word> uniqueWords = getUniqueWords();
    for (Word word : uniqueWords) {
      if (word.isPalindrome()) {
        polindromes.add(word);
      }
    }
    return polindromes;
  }

  public int getPolindromesAmount() {
    return getPolindromes().size();
  }

  @NonNull
  public int calculateLongestWordLength() {
    Word longestWordInText = new Word("");
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    if (!sentences.isEmpty()) {
      Sentence firstSentence = sentences.iterator().next();
      longestWordInText = firstSentence.getFirstLongestWord();
      for (Sentence sentence : sentences) {
        Word longestWordInSentence = sentence.getFirstLongestWord();
        if (longestWordInSentence.isMeaningfullWord() && longestWordInSentence.compareTo(longestWordInText) > 0) {
          longestWordInText = longestWordInSentence;
        }
      }
    }
    return longestWordLength = longestWordInText.getLength();
  }

  @NonNull
  public int getShortestWordLength() {
    Word shortestWordInText = new Word(text);
    Objects.requireNonNull(sentences, SENTENCE_NON_NULL);
    if (!sentences.isEmpty()) {
      Sentence firstSentence = sentences.iterator().next();
      shortestWordInText = firstSentence.getFirstShortestWord();
      for (Sentence sentence : sentences) {
        Word shortestWordInSentence = sentence.getFirstShortestWord();
        if (shortestWordInSentence.isMeaningfullWord() && shortestWordInSentence.compareTo(shortestWordInText) <= 0) {
          shortestWordInText = shortestWordInSentence;
        }
      }
    }
    return shortestWordInText.getLength();
  }

  @NonNull
  public Set<Word> getLongestWords() {
    int longestWordInTextLength = calculateLongestWordLength();
    Set<Word> longestWords = new HashSet<>();
    Set<Word> uniqueWords = getUniqueWords();
    for (Word word : uniqueWords) {
      if (word.getLength() == longestWordInTextLength) {
        longestWords.add(word);
      }
    }
    return longestWords;
  }

  @NonNull
  public Set<Word> getShortestWords() {
    int shortestWordInTextLength = getShortestWordLength();
    Set<Word> shortestWords = new HashSet<>();
    Set<Word> uniqueWords = getUniqueWords();
    for (Word word : uniqueWords) {
      if (word.getLength() == shortestWordInTextLength) {
        shortestWords.add(word);
      }
    }
    return shortestWords;
  }

  @Override
  public String toString() {
    return  text;
  }
}
