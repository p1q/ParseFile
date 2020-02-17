package com.parsefile.view;

import org.apache.log4j.Logger;

public class UserInterface {
    public static final int MENU_ITEM_NUMBER = 2;
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
        System.out.println("              2. Exit.                                       ");
        System.out.println("                                                             ");
        System.out.printf("Please choose a menu item (1-%d inclusive): ", MENU_ITEM_NUMBER);
    }

    public void showIncorrectRangeMessage() {
        LOGGER.warn("Incorrect range entered.");
        System.out.print("Incorrect range, please try again: ");
    }

    public void showWrongDataMessage(NumberFormatException nfe) {
        LOGGER.warn("Wrong data entered. " + nfe.getMessage());
        System.out.println("Wrong data, please enter an integer: ");
    }

    public void showFileOpenErrorMessage() {
        LOGGER.error("File open error.");
        System.out.println("File open error.");
    }

    public void showPathToFileMessage() {
        System.out.print("Please enter full path to the file: ");
    }

    public void showUnexpectedValueMessage(int value) {
        LOGGER.error("Unexpected value: " + value);
        throw new IllegalStateException("Unexpected value: " + value);
    }
}
