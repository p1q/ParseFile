package com.parsefile.service.impl;

import com.parsefile.model.Statistics;
import com.parsefile.model.WordDuplication;
import com.parsefile.service.FileParse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileParser implements FileParse {
    @Override
    public Optional<List> openFile(String filePath) {
        Path path = Paths.get(filePath.trim());
        try (Stream<String> lineStream = Files.lines(path)) {
            List<String> fileLines = lineStream.collect(Collectors.toList());
            return Optional.of(fileLines);
        } catch (IOException ioe) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Statistics>> calculateStatistics(List<String> fileLines) {
        if (fileLines == null || fileLines.isEmpty()) {
            return Optional.empty();
        } else {
            List<Statistics> statistics = new ArrayList<>();
            for (String fileLine : fileLines) {
                if (fileLine != null) {
                    String line = fileLine.replaceAll("([.,?!:;()@#$%&^<>'])", "");

                    Statistics statisticsItem = new Statistics(getLineLongestWord(line),
                            getLineShortestWord(line), fileLine.length(), getLineWordsNumber(line),
                            getLineAverageWordLength(line), getLineDuplicates(line));
                    statistics.add(statisticsItem);
                }
            }
            return Optional.of(statistics);
        }
    }

    @Override
    public Optional<Statistics> calculateWholeFileStatistics(List<Statistics> linesStatistics) {
        if (linesStatistics == null || linesStatistics.isEmpty()) {
            return Optional.empty();
        } else {
            String longestFileWord = getLongestFileWord(linesStatistics);
            String shortestFileWord = getShortestFileWord(linesStatistics);

            int lineFileLength = linesStatistics
                    .stream()
                    .mapToInt(Statistics::getLineLength)
                    .sum();

            int fileWordsQuantity = linesStatistics
                    .stream()
                    .mapToInt(Statistics::getWordsQuantity)
                    .sum();

            int averageFileWordLength = getAverageFileWordLength(linesStatistics,
                    fileWordsQuantity);

            List<WordDuplication> wordFileDuplications = getWordFileDuplications(linesStatistics);

            Statistics statistics = new Statistics(longestFileWord, shortestFileWord,
                    lineFileLength, fileWordsQuantity, averageFileWordLength, wordFileDuplications);

            return Optional.of(statistics);
        }
    }

    private int getLineWordsNumber(String line) {
        return line.split("\\s").length;
    }

    private int getAverageFileWordLength(List<Statistics> linesStatistics, int fileWordsQuantity) {
        int averageFileWordLength = 0;
        if (fileWordsQuantity != 0) {
            averageFileWordLength = linesStatistics
                    .stream()
                    .mapToInt(Statistics::getAverageWordLength)
                    .sum() / fileWordsQuantity;
        }
        return averageFileWordLength;
    }

    private List<WordDuplication> getWordFileDuplications(List<Statistics> linesStatistics) {
        List<WordDuplication> tempFileDuplications = new ArrayList<>();
        List<WordDuplication> wordFileDuplications = new ArrayList<>();
        linesStatistics
                .stream()
                .map(Statistics::getWordDuplications)
                .forEachOrdered(tempFileDuplications::addAll);

        for (int i = 0; i < tempFileDuplications.size(); i++) {
            if (!tempFileDuplications.get(i).getDuplicate().equals("")) {
                wordFileDuplications.add(tempFileDuplications.get(i));
            } else {
                continue;
            }
            for (int j = i + 1; j < tempFileDuplications.size(); j++) {
                if (wordFileDuplications.get(i).getDuplicate()
                        .equals(tempFileDuplications.get(j).getDuplicate())) {
                    wordFileDuplications.get(i).setQuantity(wordFileDuplications.get(i)
                            .getQuantity() + tempFileDuplications.get(j).getQuantity());
                    tempFileDuplications.get(j).setDuplicate("");
                }
            }
        }
        return wordFileDuplications;
    }

    private String getLongestFileWord(List<Statistics> linesStatistics) {
        String longestFileWord = linesStatistics.get(0).getLongestWord();
        for (int i = 0; i < linesStatistics.size() - 1; i++) {
            if (longestFileWord.length()
                    <= linesStatistics.get(i + 1).getLongestWord().length()) {
                longestFileWord = linesStatistics.get(i + 1).getLongestWord();
            }
        }
        return longestFileWord;
    }

    private String getShortestFileWord(List<Statistics> linesStatistics) {
        String shortestFileWord = "";
        for (int i = 0; i < linesStatistics.size() - 1; i++) {
            if (shortestFileWord.length()
                    >= linesStatistics.get(i + 1).getShortestWord().length()) {
                shortestFileWord = linesStatistics.get(i + 1).getShortestWord();
            }
        }
        return shortestFileWord;
    }

    private String getLineLongestWord(String line) {
        String longestWord = "";
        String[] words = line.split("\\s");
        for (String word : words) {
            if (word.length() >= longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    private String getLineShortestWord(String line) {
        String shortestWord = line;
        String[] words = line.split("\\s");
        for (String word : words) {
            if (word.length() <= shortestWord.length()) {
                shortestWord = word;
            }
        }
        return shortestWord;
    }

    private int getLineAverageWordLength(String line) {
        int wordsLength;
        String[] words = line.split("\\s");
        wordsLength = Arrays
                .stream(words)
                .mapToInt(String::length)
                .sum();
        return words.length == 0 ? 0 : wordsLength / words.length;
    }

    private List<WordDuplication> getLineDuplicates(String line) {
        List<WordDuplication> wordDuplications = new ArrayList<>();
        String[] words = line.split("\\s");
        if (words.length <= 1) {
            return wordDuplications;
        }
        Arrays.sort(words);
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].equals(words[i + 1])) {
                wordDuplications.add(new WordDuplication());
                wordDuplications.get(wordDuplications.size() - 1).setDuplicate(words[i]);
                wordDuplications.get(wordDuplications.size() - 1).incrementQuantity();
                while (i < (words.length - 1) && words[i].equals(words[i + 1])) {
                    wordDuplications.get(wordDuplications.size() - 1).incrementQuantity();
                    i++;
                }
            }
        }
        return wordDuplications;
    }
}
