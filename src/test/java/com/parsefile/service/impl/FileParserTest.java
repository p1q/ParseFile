package com.parsefile.service.impl;

import com.parsefile.model.Statistics;
import com.parsefile.service.FileParse;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;

public class FileParserTest {
    @Test
    public void openDemoFileInResourcesFolderShouldReturnFileContentInsideOptional() {
        FileParse fileParse = new FileParser();

        assertEquals(Optional.of(getListWithFileLines()),
                fileParse.openFile(".\\src\\main\\resources\\demo.txt"));
    }

    @Test
    public void openFileWithWittinglyInvalidPathShouldReturnOptionalEmpty() {
        FileParse fileParse = new FileParser();

        assertEquals(Optional.empty(), fileParse.openFile("c\\demo.txt\\"));
    }

    @Test
    public void calculateStatisticsWithEmptyInputListShouldReturnOptionalEmpty() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = new ArrayList<>();

        assertEquals(Optional.empty(), fileParse.calculateStatistics(fileLines));
    }

    @Test
    public void calculateStatisticsWithNullInputShouldReturnOptionalEmpty() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = null;

        assertEquals(Optional.empty(), fileParse.calculateStatistics(fileLines));
    }

    @Test
    public void calculateFullStatisticsOfBundledDemoFile() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = getListWithFileLines();
        List<Statistics> statistics = fileParse.calculateStatistics(fileLines).get();

        assertEquals("integrity", statistics.get(0).getLongestWord());
        assertEquals("of", statistics.get(0).getShortestWord());
        assertEquals(89, statistics.get(0).getLineLength());
        assertEquals(4, statistics.get(0).getAverageWordLength());
        assertEquals("of", statistics.get(0).getWordDuplications().get(0).getDuplicate());
        assertEquals(2, statistics.get(0).getWordDuplications().get(0).getQuantity());
        assertEquals("text", statistics.get(0).getWordDuplications().get(1).getDuplicate());
        assertEquals(2, statistics.get(0).getWordDuplications().get(1).getQuantity());
        assertEquals("the", statistics.get(0).getWordDuplications().get(2).getDuplicate());
        assertEquals(4, statistics.get(0).getWordDuplications().get(2).getQuantity());
    }

    private List<String> getListWithFileLines() {
        List<String> fileLines = new ArrayList<>();
        fileLines.add("Thus, the following features of the text, follow from the semantic integrity of the text:");
        fileLines.add("");
        fileLines.add("A text is a beginning statement on a particular topic!");
        fileLines.add("The text implements, the speaker’s intention, choose the main idea?");
        fileLines.add("Text of any size is a relatively autonomous (complete) statement;");
        fileLines.add("Suggestions are logically, interconnected;");
        fileLines.add("You can choose a title for choose the text;");
        fileLines.add("Correctly written text usually has a beginning and an end.");
        return fileLines;
    }
}
