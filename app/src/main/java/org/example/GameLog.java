package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;

    public void recordResult(String result) {
        switch (result) {
            case "X":
                xWins++;
                break;
            case "O":
                oWins++;
                break;
            case "Tie":
                ties++;
                break;
        }
    }

    public int getXWins() {
        return xWins;
    }

    public int getOWins() {
        return oWins;
    }

    public int getTies() {
        return ties;
    }

    public void printLog() {
        System.out.println("The current log is:\n");
        System.out.println("Player X Wins\t" + xWins);
        System.out.println("Player O Wins\t" + oWins);
        System.out.println("Ties\t\t" + ties);
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Game Log Summary:\n");
            writer.write("Player X Wins: " + xWins + "\n");
            writer.write("Player O Wins: " + oWins + "\n");
            writer.write("Ties: " + ties + "\n");
            System.out.println("\nWriting the game log to disk. Please see " + filename + " for the final statistics!");
        } catch (IOException e) {
            System.out.println("Failed to write game log: " + e.getMessage());
        }
    }
}
