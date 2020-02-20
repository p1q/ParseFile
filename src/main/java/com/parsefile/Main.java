package com.parsefile;

import com.parsefile.model.Statistics;
import com.parsefile.service.FileParse;
import com.parsefile.service.impl.FileParser;
import com.parsefile.view.UserInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        FileParse fileParse = new FileParser();
        List<Statistics> linesStatistics = null;
        Statistics fileStatistics = null;
        List<String> fileLines = new ArrayList<>();
        int menuItem;

        do {
            userInterface.showMenu();
            menuItem = getInput();
            switch (menuItem) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    UserInterface.showPathToFileMessage();
                    String filePath = scanner.nextLine();
                    Optional<List> fileData = fileParse.openFile(filePath);
                    if (fileData.isEmpty()) {
                        UserInterface.showFileOpenErrorMessage();
                    } else {
                        fileLines = fileData.get();
                        UserInterface.showFileOpenSuccessMessage();
                    }
                    break;

                case 2:
                    userInterface.showFileContents(fileLines);
                    break;

                case 3:
                    if (fileLines.isEmpty()) {
                        UserInterface.showOpenFileMessage();
                        UserInterface.pressEnterToContinue();
                    } else {
                        Optional<List<Statistics>> optionalLinesStatistics
                                = fileParse.calculateLineStatistics(fileLines);
                        if (optionalLinesStatistics.isPresent()) {
                            linesStatistics = optionalLinesStatistics.get();
                        } else {
                            UserInterface.showStatisticsCalculationErrorMessage();
                            break;
                        }

                        Optional<Statistics> optionalFileStatistics
                                = fileParse.calculateFileStatistics(linesStatistics, fileLines);

                        if (optionalFileStatistics.isPresent()) {
                            fileStatistics = optionalFileStatistics.get();
                        } else {
                            UserInterface.showStatisticsCalculationErrorMessage();
                        }
                    }
                    break;

                case 4:
                    if (linesStatistics == null) {
                        UserInterface.showCalculateStatisticsMessage();
                    }
                    break;

                case 5:
                    if (fileStatistics == null) {
                        UserInterface.showCalculateStatisticsMessage();
                    }
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;
                default:
                    UserInterface.showUnexpectedValueMessage(menuItem);
            }
        } while (menuItem != UserInterface.MENU_ITEM_NUMBER);
    }

    public static int getInput() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input > 0 && input <= UserInterface.MENU_ITEM_NUMBER) {
                    return input;
                } else {
                    UserInterface.showIncorrectRangeMessage();
                }
            } catch (NumberFormatException nfe) {
                UserInterface.showWrongDataMessage(nfe);
            }
        } while (true);
    }
}
