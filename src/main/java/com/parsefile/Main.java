package com.parsefile;

import com.parsefile.service.FileParse;
import com.parsefile.service.impl.FileParser;
import com.parsefile.view.UserInterface;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        int menuItem;
        do {
            userInterface.showMenu();
            menuItem = getInput(userInterface);
            switch (menuItem) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    String filePath = scanner.nextLine();

                    FileParse fileParse = new FileParser();
                    fileParse.openFile(filePath);
                    break;
                case 2:
                    break;
                default:
                    userInterface.showUnexpectedValueMessage(menuItem);
            }
        } while (menuItem != 0);
    }

    public static int getInput(UserInterface userInterface) {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input > 0 && input <= UserInterface.MENU_ITEM_NUMBER) {
                    return input;
                } else {
                    userInterface.showIncorrectRangeMessage();
                }
            } catch (NumberFormatException nfe) {
                userInterface.showWrongDataMessage(nfe);
            }
        } while (true);
    }
}
