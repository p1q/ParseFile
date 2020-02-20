package com.parsefile.model;

import java.util.List;

public class Statistics {
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private int wordsQuantity;
    private int averageWordLength;
    private int nonSpaceSymbolQuantity;
    private List<WordDuplication> wordDuplications;

    public Statistics(String longestWord, String shortestWord, int lineLength,
                      int wordsQuantity, int averageWordLength, int nonSpaceSymbolQuantity,
                      List<WordDuplication> wordDuplications) {
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.lineLength = lineLength;
        this.wordsQuantity = wordsQuantity;
        this.averageWordLength = averageWordLength;
        this.nonSpaceSymbolQuantity = nonSpaceSymbolQuantity;
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

    public int getNonSpaceSymbolQuantity() {
        return nonSpaceSymbolQuantity;
    }

    public void setNonSpaceSymbolQuantity(int nonSpaceSymbolQuantity) {
        this.nonSpaceSymbolQuantity = nonSpaceSymbolQuantity;
    }

    public void setWordDuplications(List<WordDuplication> wordDuplications) {
        this.wordDuplications = wordDuplications;
    }
}
