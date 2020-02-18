package com.parsefile.service.impl;

import com.parsefile.service.FileParse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void calculateStatistics() {

    }
}
