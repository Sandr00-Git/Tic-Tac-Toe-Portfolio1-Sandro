package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerPlayerTest {

    @Test
    public void testFirstMoveChoosesCorner() {
        Board board = new Board();
        ComputerPlayer bot = new ComputerPlayer('X');
        int move = bot.chooseMove(board);
        assertTrue(move == 0 || move == 2 || move == 6 || move == 8);
    }

    @Test
    public void testSecondMoveChoosesCenter() {
        Board board = new Board();
        board.makeMove(0, 'X'); // First player moved
        ComputerPlayer bot = new ComputerPlayer('O');
        int move = bot.chooseMove(board);
        assertEquals(4, move); // Should pick center
    }

    @Test
    public void testWinningMoveTaken() {
        Board board = new Board();
        board.makeMove(0, 'O');
        board.makeMove(1, 'O');
        board.makeMove(4, 'X');
        board.makeMove(5, 'X');
        ComputerPlayer bot = new ComputerPlayer('O');
        int move = bot.chooseMove(board);
        assertEquals(2, move); // Bot should win by placing at position 2
    }

    @Test
    public void testBlockingMoveTaken() {
        Board board = new Board();
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(3, 'O'); // Random bot move
        ComputerPlayer bot = new ComputerPlayer('O');
        int move = bot.chooseMove(board);
        assertEquals(2, move); // Bot should block X from winning
    }

    @Test
    public void testRandomMoveFallback() {
        Board board = new Board();
        // Fill all but a few random cells
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X');
        // Available: 7, 8
        ComputerPlayer bot = new ComputerPlayer('O');
        int move = bot.chooseMove(board);
        assertTrue(move == 7 || move == 8); // Must pick one of the remaining
    }
}
