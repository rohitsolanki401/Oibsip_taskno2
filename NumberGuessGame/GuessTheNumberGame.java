package NumberGuessGame;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            int totalScore = 0;
            int rounds = 1;
            boolean roundInProgress;

            System.out.println("Welcome to the Guess the Number Game!");

            do {
                int numberToGuess = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
                int attempts = 0;
                roundInProgress = true;

                System.out.println("\nRound " + rounds + ": I have selected a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
                System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

                while (attempts < MAX_ATTEMPTS && roundInProgress) {
                    System.out.print("Enter your guess: ");
                    int userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess < LOWER_BOUND || userGuess > UPPER_BOUND) {
                        System.out.println("Please enter a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
                    } else if (userGuess < numberToGuess) {
                        System.out.println("Higher! Try again.");
                    } else if (userGuess > numberToGuess) {
                        System.out.println("Lower! Try again.");
                    } else {
                        roundInProgress = false;
                        int points = calculatePoints(attempts);
                        totalScore += points;
                        System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts. You earned " + points + " points.");
                    }
                }

                if (roundInProgress) {
                    System.out.println("Sorry! The correct number was " + numberToGuess + ".");
                }

                rounds++;
            } while (roundInProgress);

            System.out.println("Your total score after " + (rounds - 1) + " rounds is: " + totalScore);

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("Thank you for playing! Goodbye!");
        scanner.close();
    }

    private static int calculatePoints(int attempts) {
        if (attempts == 1) {
            return 10;  // Full points for guessing on the first attempt
        } else if (attempts <= MAX_ATTEMPTS) {
            return Math.max(0, 10 - (attempts - 1)); // Reduce points based on attempts
        }
        return 0; // No points if maximum attempts are exceeded
    }
}
