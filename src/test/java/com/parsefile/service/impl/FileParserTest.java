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

        assertEquals(Optional.empty(), fileParse.calculateLineStatistics(fileLines));
    }

    @Test
    public void calculateStatisticsWithNullInputShouldReturnOptionalEmpty() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = null;

        assertEquals(Optional.empty(), fileParse.calculateLineStatistics(fileLines));
    }

    @Test
    public void calculateLinesStatisticsOfBundledDemoFile() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = getListWithFileLines();
        List<Statistics> statistics = fileParse.calculateLineStatistics(fileLines).get();

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

    @Test
    public void calculateLinesStatisticsOfGivenLines() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = getListWithGivenLines();
        List<Statistics> statistics = fileParse.calculateLineStatistics(fileLines).get();

        assertEquals("", statistics.get(0).getLongestWord());
        assertEquals("", statistics.get(0).getShortestWord());
        assertEquals(0, statistics.get(0).getLineLength());
        assertEquals(0, statistics.get(0).getAverageWordLength());

        assertEquals("zzz", statistics.get(1).getLongestWord());
        assertEquals("zzz", statistics.get(1).getShortestWord());
        assertEquals(55, statistics.get(1).getLineLength());
        assertEquals(3, statistics.get(1).getAverageWordLength());
        assertEquals("aaa", statistics.get(1).getWordDuplications().get(0).getDuplicate());
        assertEquals(3, statistics.get(1).getWordDuplications().get(0).getQuantity());
        assertEquals("bbb", statistics.get(1).getWordDuplications().get(1).getDuplicate());
        assertEquals(3, statistics.get(1).getWordDuplications().get(1).getQuantity());
        assertEquals("zzz", statistics.get(1).getWordDuplications().get(2).getDuplicate());
        assertEquals(4, statistics.get(1).getWordDuplications().get(2).getQuantity());

        assertEquals("LongestWord", statistics.get(2).getLongestWord());
        assertEquals("SW", statistics.get(2).getShortestWord());
        assertEquals(66, statistics.get(2).getLineLength());
        assertEquals(3, statistics.get(2).getAverageWordLength());
        assertEquals("aaa", statistics.get(2).getWordDuplications().get(0).getDuplicate());
        assertEquals(3, statistics.get(2).getWordDuplications().get(0).getQuantity());
        assertEquals("bbb", statistics.get(2).getWordDuplications().get(1).getDuplicate());
        assertEquals(3, statistics.get(2).getWordDuplications().get(1).getQuantity());
        assertEquals("zzz", statistics.get(2).getWordDuplications().get(2).getDuplicate());
        assertEquals(4, statistics.get(2).getWordDuplications().get(2).getQuantity());
    }

    @Test
    public void calculateFileStatisticsOfGivenLines() {
        FileParse fileParse = new FileParser();
        List<String> fileLines = getListWithGivenLines();
        List<Statistics> linesStatistics = fileParse.calculateLineStatistics(fileLines).get();
        Statistics wholeFileStatistics
                = fileParse.calculateFileStatistics(linesStatistics, getListWithGivenLines()).get();

        assertEquals("LongestWord", wholeFileStatistics.getLongestWord());
        assertEquals("", wholeFileStatistics.getShortestWord());
        assertEquals(170, wholeFileStatistics.getLineLength());
        assertEquals(2, wholeFileStatistics.getAverageWordLength());
        assertEquals("aaa", wholeFileStatistics.getWordDuplications().get(0).getDuplicate());
        assertEquals(9, wholeFileStatistics.getWordDuplications().get(0).getQuantity());
        assertEquals("bbb", wholeFileStatistics.getWordDuplications().get(1).getDuplicate());
        assertEquals(8, wholeFileStatistics.getWordDuplications().get(1).getQuantity());
        assertEquals("fds", wholeFileStatistics.getWordDuplications().get(2).getDuplicate());
        assertEquals(2, wholeFileStatistics.getWordDuplications().get(2).getQuantity());
        assertEquals("ghy", wholeFileStatistics.getWordDuplications().get(3).getDuplicate());
        assertEquals(2, wholeFileStatistics.getWordDuplications().get(3).getQuantity());
        assertEquals("jkd", wholeFileStatistics.getWordDuplications().get(4).getDuplicate());
        assertEquals(2, wholeFileStatistics.getWordDuplications().get(4).getQuantity());
        assertEquals("uuu", wholeFileStatistics.getWordDuplications().get(5).getDuplicate());
        assertEquals(2, wholeFileStatistics.getWordDuplications().get(5).getQuantity());
        assertEquals("zzz", wholeFileStatistics.getWordDuplications().get(6).getDuplicate());
        assertEquals(9, wholeFileStatistics.getWordDuplications().get(6).getQuantity());
    }

    private List<String> getListWithGivenLines() {
        List<String> fileLines = new ArrayList<>();
        fileLines.add("");
        fileLines.add("aaa aaa kkk aaa ghy jkd bbb bbb bbb fds zzz zzz zzz zzz");
        fileLines.add("LongestWord aaa aaa aaa ghy jkd bbb bbb bbb fds zzz zzz zzz zzz SW");
        fileLines.add("aaa aaa");
        fileLines.add("");
        fileLines.add("");
        fileLines.add("zzz bbb");
        fileLines.add("fff bbb uuu uuu");
        fileLines.add("ooo sss");
        fileLines.add("aaa");
        fileLines.add("qqq");
        fileLines.add("       ");
        fileLines.add("");
        return fileLines;
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
