import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;

    public Game() {
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        boolean playing = true;

        while (playing) {
            board = new Board();
            playOneGame();
            playing = askToPlayAgain();
        }

        System.out.println("Goodbye!");
    }

    private void playOneGame() {
        char currentPlayer = 'X';

        while (true) {
            board.display();
            int move = getValidMove(currentPlayer);
            board.makeMove(move, currentPlayer);

            if (board.checkWin(currentPlayer)) {
                board.display();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (board.isFull()) {
                board.display();
                System.out.println("It's a draw!");
                break;
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

    private boolean askToPlayAgain() {
        while (true) {
            System.out.print("Would you like to play again (yes/no)? ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("That is not a valid entry!");
            }
        }
    }
}
