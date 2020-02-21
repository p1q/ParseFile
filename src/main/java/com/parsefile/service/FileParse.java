package com.parsefile.service;

import com.parsefile.model.Statistics;
import java.util.List;
import java.util.Optional;

public interface FileParse {
    Optional<List> openFile(String filePath);

    Optional<List<Statistics>> calculateLineStatistics(List<String> fileLines);

    Optional<Statistics> calculateFileStatistics(List<Statistics> linesStatistics,
                                                 List<String> fileLines);

    void saveStatistics(List<Statistics> linesStatistics, Statistics fileStatistics);
}
