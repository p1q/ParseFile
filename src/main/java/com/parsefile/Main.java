package com.parsefile;

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
        int menuItem;
        List<String> fileLines= new ArrayList<>();
        do {
            userInterface.showMenu();
            menuItem = getInput(userInterface);
            switch (menuItem) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    userInterface.showPathToFileMessage();
                    String filePath = scanner.nextLine();
                    FileParse fileParse = new FileParser();
                    Optional<List> fileData= fileParse.openFile(filePath);
                    if (fileData.isEmpty()) {
                        userInterface.showFileOpenErrorMessage();
                    } else {
                        fileLines = fileData.get();
                        userInterface.showFileOpenSuccessMessage();
                    }
                    break;
                case 2:
                    userInterface.showFileContents(fileLines);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    userInterface.showUnexpectedValueMessage(menuItem);
            }
        } while (menuItem != UserInterface.MENU_ITEM_NUMBER);
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
