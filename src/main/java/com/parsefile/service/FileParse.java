package com.parsefile.service;

import com.parsefile.model.Statistics;

import java.util.List;
import java.util.Optional;

public interface FileParse {
    Optional<List> openFile(String filePath);
    Optional<List<Statistics>> calculateStatistics(List<String> fileLines);

}
