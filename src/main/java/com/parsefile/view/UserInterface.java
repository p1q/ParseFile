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
            System.out.println("Please open a file first.");
        } else {
            System.out.println("Full file contents: ");
            fileLines.forEach(System.out::println);
        }
    }

    public void showIncorrectRangeMessage() {
        LOGGER.warn("Incorrect range entered.");
        System.out.print("Incorrect range, please try again: ");
    }

    public void showWrongDataMessage(NumberFormatException nfe) {
        LOGGER.warn("Wrong data entered. " + nfe.getMessage());
        System.out.print("Wrong data, please enter an integer: ");
    }

    public void showFileOpenErrorMessage() {
        LOGGER.error("File open error.");
        System.out.println("File open error.");
    }

    public void showFileOpenSuccessMessage() {
        System.out.println("File was opened successfully.");
    }

    public void showPathToFileMessage() {
        System.out.print("Please enter full path to the file: ");
    }

    public void showUnexpectedValueMessage(int value) {
        LOGGER.error("Unexpected value: " + value);
        throw new IllegalStateException("Unexpected value: " + value);
    }
}
