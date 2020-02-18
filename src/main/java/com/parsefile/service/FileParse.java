package com.parsefile.service;

import java.util.List;
import java.util.Optional;

public interface FileParse {
    Optional<List> openFile(String filePath);
    void calculateStatistics();


}
