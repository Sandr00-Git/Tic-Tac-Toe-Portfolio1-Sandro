package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testInitialBoardIsEmpty() {
        Board board = new Board();
        for (int i = 0; i < 9; i++) {
            assertTrue(board.isCellEmpty(i));
        }
    }

    @Test
    public void testMakeMoveAndCheckWin() {
        Board board = new Board();
        board.makeMove(0, 'X');
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        assertTrue(board.checkWin('X'));
    }

    @Test
    public void testIsFull() {
        Board board = new Board();
        for (int i = 0; i < 9; i++) {
            board.makeMove(i, 'X');
        }
        assertTrue(board.isFull());
    }
}
