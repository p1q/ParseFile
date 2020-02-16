package com.parsefile;

import org.apache.log4j.Logger;
import service.FileParse;
import service.impl.FileParser;

import java.util.Scanner;

public class Menu {
    private static final int MENU_ITEM_NUMBER = 2;
    private static final Logger LOGGER = Logger.getLogger(Menu.class);

    public static void main(String[] args) {
        // Choose menu item
        while (true) {
            printMenu();
            switch (getInput()) {
                case 1:
                    FileParse fileParse = new FileParser();
                    fileParse.readFile();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    LOGGER.error("Unexpected value: " + getInput());
                    throw new IllegalStateException("Unexpected value: " + getInput());
            }
        }
    }

    // Menu output
    private static void printMenu() {
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
    }

    // Read entered data
    static int getInput() {
        System.out.printf("Please choose a menu item (1-%d inclusive): ", MENU_ITEM_NUMBER);
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input > 0 && input <= MENU_ITEM_NUMBER) {
                    return input;
                } else {
                    LOGGER.warn("Incorrect range entered.");
                    System.out.print("Incorrect range, please try again: ");
                }
            } catch (NumberFormatException nfe) {
                LOGGER.warn("Wrong data entered. " + nfe.getMessage());
                System.out.println("Wrong data, please enter an integer: ");
            }
        } while (true);
    }
}
