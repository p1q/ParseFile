package com.parsefile.model;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private int averageWordLength;
    private List<WordDuplication> wordDuplications = new ArrayList<>();

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

    public void setWordDuplications(List<WordDuplication> wordDuplications) {
        this.wordDuplications = wordDuplications;
    }
}
