package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {

    @Test
    public void testRecordXWin() {
        GameLog log = new GameLog();
        log.recordResult("X");
        assertEquals(1, log.getXWins());
        assertEquals(0, log.getOWins());
        assertEquals(0, log.getTies());
    }

    @Test
    public void testRecordOWin() {
        GameLog log = new GameLog();
        log.recordResult("O");
        assertEquals(0, log.getXWins());
        assertEquals(1, log.getOWins());
        assertEquals(0, log.getTies());
    }

    @Test
    public void testRecordTie() {
        GameLog log = new GameLog();
        log.recordResult("Tie");
        assertEquals(0, log.getXWins());
        assertEquals(0, log.getOWins());
        assertEquals(1, log.getTies());
    }

    @Test
    public void testMultipleRounds() {
        GameLog log = new GameLog();
        log.recordResult("X");
        log.recordResult("O");
        log.recordResult("Tie");
        log.recordResult("X");
        assertEquals(2, log.getXWins());
        assertEquals(1, log.getOWins());
        assertEquals(1, log.getTies());
    }
}
