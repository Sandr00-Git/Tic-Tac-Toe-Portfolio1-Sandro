package org.example;

import org.example.Board;
import org.example.GameLog;

import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;
    private GameLog gameLog;

    public Game() {
        scanner = new Scanner(System.in);
        gameLog = new GameLog();
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playing = true;
        char currentStarter = 'X';  // Start with X by default

        while (playing) {
            board = new Board();
            String result = playOneGame(currentStarter);
            gameLog.recordResult(result);
            gameLog.printLog();

            // Swap starting player (loser goes first, winner goes second)
            if (result.equals("X")) {
                currentStarter = 'O';
            } else if (result.equals("O")) {
                currentStarter = 'X';
            } // If tie, no change

            playing = askToPlayAgain(currentStarter);
        }

        gameLog.saveToFile("game.txt");
        System.out.println("Goodbye!");
    }

    private String playOneGame(char firstPlayer) {
        char currentPlayer = firstPlayer;

        while (true) {
            board.display();
            int move = getValidMove(currentPlayer);
            board.makeMove(move, currentPlayer);

            if (board.checkWin(currentPlayer)) {
                board.display();
                System.out.println("Player " + currentPlayer + " wins!");
                return String.valueOf(currentPlayer);
            } else if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                return "Tie";
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private int getValidMove(char player) {
        while (true) {
            System.out.print("Player " + player + ", what is your move? ");
            String input = scanner.nextLine().trim();

            try {
                int pos = Integer.parseInt(input) - 1;
                if (pos >= 0 && pos < 9) {
                    if (board.isCellEmpty(pos)) {
                        return pos;
                    } else {
                        System.out.println("That cell is already taken! Try again.");
                    }
                } else {
                    System.out.println("That is not a valid move! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a valid move! Try again.");
            }
        }
    }

    private boolean askToPlayAgain(char nextStarter) {
        while (true) {
            System.out.print("Would you like to play again (yes/no)? ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                System.out.println("\nGreat! This time " + nextStarter + " will go first!\n");
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("That is not a valid entry!");
            }
        }
    }
}
