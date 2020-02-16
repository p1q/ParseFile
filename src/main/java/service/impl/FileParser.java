package service.impl;

import service.FileParse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileParser implements FileParse {
    @Override
    public void readFile() {
        System.out.println("Please enter full path to the file: ");
        Scanner scanner = new Scanner(System.in);
        List<String> fileLines = new ArrayList<>();
        Path filePath = Paths.get(scanner.nextLine().trim());

        try (Stream<String> lineStream = Files.lines(filePath)) {
            fileLines = lineStream.collect(Collectors.toList());
        } catch (IOException ioe) {
            }
    }
}
