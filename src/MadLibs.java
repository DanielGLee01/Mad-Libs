// Daniel Lee
// 3/4/22
// CSE 142 Section AJ
// TA: Jacqueline Jou
// Take-Home Assessment 6
// Mad Libs
// This program asks the user for an input file, which contains
// placeholders for certain types of words. The user replaces the
// placeholders with their own words, then the program puts the
// complete message into an output text file. The program can also
// view any mad lib text file, and display it in the console.

import java.io.*;
import java.util.*;

public class MadLibs {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        boolean activeGame = true;

        introMessage();

        // This while loop controls the menu of the program. Based on
        // user input, the menu will go to createLib, viewLib or quit
        // the program. If the user input does not match the prompts,
        // the program will reprompt the user until a valid input is
        // given.
        while (activeGame) {
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            String answer = console.nextLine().toLowerCase();

            if (answer.equals("c")) {
                createLib(console, inputFile(console));
                activeGame = true;
            } else if (answer.equals("v")) {
                viewLib(inputFile(console));
                activeGame = true;
            } else if (answer.equals("q")) {
                System.out.println();
                activeGame = false;
            }
        }
    }

    // Displays an introduction message to the user
    public static void introMessage() {
        System.out.println("Welcome to the game of Mad Libs.");
        System.out.println("I will ask you to provide various words");
        System.out.println("and phrases to fill in a story.");
        System.out.println("The result will be written to an output file.");
        System.out.println();
    }

    // Asks for the name of the input file for mad libs, and reprompts the user
    // if the file name that is provided cannot be found.
    // Parameters: console - Takes the user's input from the console
    // The file is then returned.
    public static Scanner inputFile(Scanner console) throws FileNotFoundException {
        System.out.print("Input file name: ");
        String fileIn = console.nextLine();
        File input = new File(fileIn);

        while (!input.exists()) {
            System.out.print("File not found. Try again: ");
            fileIn = console.nextLine();
            input = new File(fileIn);
        }

        return new Scanner(input);
    }

    // The specific method used for determining the word in a placeholder, as well
    // as creating the prompt for the user to create a custom word based on the
    // placeholder.
    // Parameters: wordType - the specific word in the placeholder in the input file.
    // If there is a placeholder, the method returns true. Otherwise, the method
    // returns false.
    public static boolean customWord(String wordType) {
        if (wordType.charAt(0) == '<' && wordType.charAt(wordType.length() - 1) == '>') {
            System.out.print("Please type a");

            if ("aeiou".contains(wordType.toLowerCase().substring(1, 2))) {
                System.out.print("n");
            }
            System.out.print(" " + wordType.substring(1, wordType.length() - 1)
                    .replace("-", " ") + ": ");

            return true;
        }

        return false;
    }

    // Asks the user for the name of the output file, then replaces the
    // placeholders with the words that the user inputs. The new words and mad lib
    // are then created and stored in a new file.
    // Parameters: console - takes in input from the user through the console
    //             inFile - the file to be viewed and modified based on user input
    public static void createLib(Scanner console, Scanner inFile) throws FileNotFoundException {
        System.out.print("Output file name: ");
        String fileOut = console.nextLine();
        PrintStream display = new PrintStream(fileOut);
        System.out.println();

        while (inFile.hasNextLine()) {
            String sentence = inFile.nextLine();
            Scanner scanner = new Scanner(sentence);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (customWord(word)) {
                    display.print(console.nextLine() + " ");
                } else {
                    display.print(word + " ");
                }
            }
            display.println();
        }
        System.out.println("Your mad-lib has been created!");
        System.out.println();
    }

    // The method used for viewing a file based on the user's input.
    // Parameters: inFile - the file to be viewed based on user input
    public static void viewLib(Scanner inFile) {
        System.out.println();

        while (inFile.hasNextLine()) {
            String line = inFile.nextLine();
            System.out.println(line);
        }
        System.out.println();
    }
}