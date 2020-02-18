package com.parsefile.model;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private int averageWordLength;
    Map<String, Integer> wordsDuplications = new HashMap<String, Integer>();
}
