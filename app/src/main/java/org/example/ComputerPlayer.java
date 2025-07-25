package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private char symbol;
    private char opponent;

    public ComputerPlayer(char symbol) {
        this.symbol = symbol;
        this.opponent = (symbol == 'X') ? 'O' : 'X';
    }

    public int chooseMove(Board board) {
        char[] cells = board.getCells();

        // 1. First move: take a corner if board is empty
        if (isBoardEmpty(cells)) {
            int[] corners = {0, 2, 6, 8};
            return getRandomAvailable(corners, cells);
        }

        // 2. Second move: take center if available
        if (isSecondMove(cells) && cells[4] != 'X' && cells[4] != 'O') {
            return 4;
        }

        // 3. Win if possible
        int winMove = findWinningMove(cells, symbol);
        if (winMove != -1) return winMove;

        // 4. Block opponent's win
        int blockMove = findWinningMove(cells, opponent);
        if (blockMove != -1) return blockMove;

        // 5. Otherwise, pick a random available spot
        return getRandomAvailable(getAllIndices(), cells);
    }

    private boolean isBoardEmpty(char[] cells) {
        for (char cell : cells) {
            if (cell == 'X' || cell == 'O') return false;
        }
        return true;
    }

    private boolean isSecondMove(char[] cells) {
        int count = 0;
        for (char cell : cells) {
            if (cell == 'X' || cell == 'O') count++;
        }
        return count == 1;
    }

    private int findWinningMove(char[] cells, char player) {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] line : winPatterns) {
            int a = line[0], b = line[1], c = line[2];
            if (cells[a] == player && cells[b] == player && isOpen(cells[c])) return c;
            if (cells[a] == player && cells[c] == player && isOpen(cells[b])) return b;
            if (cells[b] == player && cells[c] == player && isOpen(cells[a])) return a;
        }
        return -1;
    }

    private boolean isOpen(char cell) {
        return cell != 'X' && cell != 'O';
    }

    private int getRandomAvailable(int[] indices, char[] cells) {
        List<Integer> available = new ArrayList<>();
        for (int i : indices) {
            if (isOpen(cells[i])) available.add(i);
        }
        if (available.isEmpty()) return -1;
        return available.get(new Random().nextInt(available.size()));
    }

    private int[] getAllIndices() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    }
}
