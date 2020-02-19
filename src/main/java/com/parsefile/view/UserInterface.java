package com.parsefile.view;

import org.apache.log4j.Logger;
import java.util.List;

public class UserInterface {
    public static final int MENU_ITEM_NUMBER = 8;
    private static final Logger LOGGER = Logger.getLogger(UserInterface.class);

    public void showMenu() {
        System.out.println("                                                             ");
        System.out.println("     +----------------------------------------------------+  ");
        System.out.println("     |                ParseFile v1.0 Beta                 |  ");
        System.out.println("     |             ------------------------               |  ");
        System.out.println("     |                   MAIN MENU                        |  ");
        System.out.println("     +----------------------------------------------------+  ");
        System.out.println("                                                             ");
        System.out.println("              1. Open a file.                                ");
        System.out.println("              2. Show current file contents.                 ");
        System.out.println("              3. Calculate statistics.                       ");
        System.out.println("              4. Show statistics for each line.              ");
        System.out.println("              5. Show statistics for the whole file.         ");
        System.out.println("              6. Save data to the database.                  ");
        System.out.println("              7. Load data from the database.                ");
        System.out.println("              8. Clear database.                             ");
        System.out.println("              9. Exit.                                       ");
        System.out.println("                                                             ");
        System.out.printf("Please choose a menu item (1-%d inclusive): ", MENU_ITEM_NUMBER);
    }

    public void showFileContents(List<String> fileLines) {
        if (fileLines == null || fileLines.size() == 0) {
            showOpenFileMessage();
        } else {
            System.out.println("Full file contents: ");
            fileLines.forEach(System.out::println);
        }
    }

    public static void pressEnterToContinue() {
        System.out.println("");
        System.out.print("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void showIncorrectRangeMessage() {
        LOGGER.warn("Incorrect range entered.");
        System.out.print("Incorrect range, please try again: ");
    }

    public static void showWrongDataMessage(NumberFormatException nfe) {
        LOGGER.warn("Wrong data entered. " + nfe.getMessage());
        System.out.print("Wrong data, please enter an integer: ");
    }

    public static void showFileOpenErrorMessage() {
        LOGGER.error("File open error.");
        System.out.println("File open error.");
    }

    public static void showFileOpenSuccessMessage() {
        System.out.println("File was opened successfully.");
    }

    public static void showPathToFileMessage() {
        System.out.print("Please enter full path to the file: ");
    }

    public static void showUnexpectedValueMessage(int value) {
        LOGGER.error("Unexpected value: " + value);
        throw new IllegalStateException("Unexpected value: " + value);
    }

    public static void showOpenFileMessage() {
        System.out.println("Please open a file first.");
    }

    public static void showCalculateStatisticsMessage() {
        System.out.println("Please calculate statistics first.");
    }

    public static void showStatisticsCalculationErrorMessage() {
        System.out.println("An error while statistic calculation was occurred!");
    }
}
