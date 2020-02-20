package com.parsefile.model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private int wordsQuantity;
    private int averageWordLength;
    private List<WordDuplication> wordDuplications = new ArrayList<>();

    public Statistics() {
    }

    public Statistics(String longestWord, String shortestWord, int lineLength, int wordsQuantity,
                      int averageWordLength, List<WordDuplication> wordDuplications) {
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.lineLength = lineLength;
        this.wordsQuantity = wordsQuantity;
        this.averageWordLength = averageWordLength;
        this.wordDuplications = wordDuplications;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public List<WordDuplication> getWordDuplications() {
        return wordDuplications;
    }

    public int getWordsQuantity() {
        return wordsQuantity;
    }

    public void setWordsQuantity(int wordsQuantity) {
        this.wordsQuantity = wordsQuantity;
    }

    public void setWordDuplications(List<WordDuplication> wordDuplications) {
        this.wordDuplications = wordDuplications;
    }
}
