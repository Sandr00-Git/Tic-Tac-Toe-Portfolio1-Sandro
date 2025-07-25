package org.example;

import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;
    private GameLog gameLog;
    private boolean vsComputer = false;
    private boolean computerIsFirst = false;
    private ComputerPlayer computer;

    public Game() {
        scanner = new Scanner(System.in);
        gameLog = new GameLog();
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");

        boolean playing = true;
        char currentStarter = 'X';

        selectGameMode();

        while (playing) {
            board = new Board();
            if (vsComputer) {
                computer = new ComputerPlayer(computerIsFirst ? 'X' : 'O');
            }

            String result = playOneGame(currentStarter);
            gameLog.recordResult(result);
            gameLog.printLog();

            // Swap starter (loser goes first)
            if (result.equals("X")) {
                currentStarter = 'O';
            } else if (result.equals("O")) {
                currentStarter = 'X';
            }

            playing = askToPlayAgain(currentStarter);
        }

        gameLog.saveToFile("game.txt");
        System.out.println("Goodbye!");
    }

    private void selectGameMode() {
        System.out.println("What kind of game would you like to play?");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");

        while (true) {
            System.out.print("What is your selection? ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    vsComputer = false;
                    return;
                case "2":
                    vsComputer = true;
                    computerIsFirst = false;
                    System.out.println("\nGreat! You will go first.");
                    return;
                case "3":
                    vsComputer = true;
                    computerIsFirst = true;
                    System.out.println("\nGreat! The computer will go first.");
                    return;
                default:
                    System.out.println("That is not a valid entry. Try again.");
            }
        }
    }

    private String playOneGame(char firstPlayer) {
        char currentPlayer = firstPlayer;

        while (true) {
            board.display();

            int move;
            if (vsComputer && currentPlayer == (computerIsFirst ? 'X' : 'O')) {
                move = computer.chooseMove(board);
                System.out.println("Computer chooses: " + (move + 1));
            } else {
                move = getValidMove(currentPlayer);
            }

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
