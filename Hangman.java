/**
 * The Hangman class implements a simple console-based Hangman game.
 * It allows users to play multiple rounds of Hangman by guessing words.
 *
 * Constants:
 * - words: Array storing words for the Hangman game.
 *
 * Main Method:
 * - Initializes game variables and starts playing Hangman using the playHangmanGame method.
 *
 * playHangmanGame Method:
 * - Manages the main game loop, allowing users to play a single round of Hangman.
 * - Invokes other methods to handle word selection, user input, and game outcome.
 *
 * getRandomWord Method:
 * - Selects a random word from the available word list.
 *
 * initializeHiddenWord Method:
 * - Initializes the hiddenWord array with asterisks based on the length of the selected word.
 *
 * displayHiddenWord Method:
 * - Displays the current state of the hiddenWord array to the user.
 *
 * getUserInput Method:
 * - Gets a valid letter guess from the user.
 *
 * isValidUserGuess Method:
 * - Checks if the user's guess is a valid letter and not repeated.
 *
 * checkUserGuess Method:
 * - Checks if the user's guess is in the word and updates the hiddenWord array.
 *
 * checkWordCompletion Method:
 * - Checks if the word is completely guessed by the user.
 *
 * displayGameOutcome Method:
 * - Displays the outcome of the game based on the user's performance.
 *
 * askUserToPlayAgain Method:
 * - Asks the user if they want to play another round of Hangman.
 */

import java.util.Scanner;
import java.util.Random;

/**
 * The HangmanGame class implements a simple console-based Hangman game.
 * It allows users to play multiple rounds of Hangman by guessing words.
 */
public class Hangman {

    /**
     * Main method to start the Hangman game.
     * This method initializes the game variables and starts playing Hangman.
     *
     * @parameter args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Initialize game variables and start playing Hangman
        playHangmanGame();
    }

    /**
     * Main game loop that plays a single round of the Hangman game.
     * Users are prompted to guess letters to uncover the hidden word.
     */
    public static void playHangmanGame() {
        // array that stores words
        String[] words = {"write", "that", "program", "hangman", "java", "array", "random", "guess"};

        // create a scanner object for user input
        Scanner input = new Scanner(System.in);

        // create a random object for word selection
        Random random = new Random();

        // initialize the game variables
        boolean continuePlaying = true; // whether to continue playing
        int totalMisses = 0; // the total number of misses in the game

        // loop until the user decides to stop playing
        while (continuePlaying) {
            // select a random word from the array
            String word = words[random.nextInt(words.length)];

            // create a char array to store the hidden word
            char[] hiddenWord = initializeHiddenWord(word);

            // Load the array with asterisks
            for (int i = 0; i < hiddenWord.length; i++) {
                hiddenWord[i] = '*';
            }

            // initialize the word variables
            int misses = 0; // the number of misses for the current word
            boolean done = false; // whether the word is guessed

            // loop until the word is guessed or the user runs out of guesses
            while (!done && misses < 7) {
                // display the hidden word
                System.out.print("(Guess) Enter a letter in word ");
                System.out.print(hiddenWord);
                System.out.print(" > ");

                // get the user's guess
                char guess = input.next().charAt(0);

                // check if the guess is valid
                if (!Character.isLetter(guess)) {
                    System.out.println("Invalid input. Please enter a letter.");
                    continue;
                }

                // check if the guess is already in the word
                boolean repeated = false;
                for (int i = 0; i < hiddenWord.length; i++) {
                    char c = hiddenWord[i];
                    if (c == guess) {
                        System.out.println(guess + " is already in the word.");
                        repeated = true;
                        break;
                    }
                }

                if (repeated) {
                    continue;
                }

                // check if the guess is in the word
                boolean correct = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        hiddenWord[i] = guess;
                        correct = true;
                    }
                }

                // if the guess is correct, check if the word is complete
                if (correct) {
                    boolean complete = true;
                    for (int i = 0; i < hiddenWord.length; i++) {
                        if (hiddenWord[i] == '*') {
                            complete = false;
                            break;
                        }
                    }

                    if (complete) {
                        String timesOrTime;
                        if (misses == 1) {
                            timesOrTime = "time";
                        } else {
                            timesOrTime = "times";
                        }

                        System.out.println("The word is " + word + ". You missed " + misses + " " + timesOrTime);
                        done = true;
                    }
                } else {
                    // if the guess is wrong, increment the misses and display a message
                    System.out.println(guess + " is not in the word.");
                    misses++;
                }
            }

            // if the user exhausts all guesses, display a message.
            if (misses == 7) {
                System.out.println("You have run out of guesses. The word was " + word + ".");
            }

            // update the total misses
            totalMisses += misses;

            // ask the user if they want to play another word: y:yes while n=no
            System.out.print("Do you want to guess another word? Enter y or n > ");
            char answer = input.next().charAt(0);
            if (answer == 'n') {
                continuePlaying = false;
            }
        }

        // close the scanner
        input.close();
    }

    /**
     * Initializes the hidden word array with asterisks.
     *
     * @parameter word The word for which the hidden word array is initialized.
     * @return The initialized hidden word array.
     */
    public static char[] initializeHiddenWord(String word) {
        return new char[word.length()];
    }
}
