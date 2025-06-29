public class Board {
    private char[] cells;

    public Board() {
        cells = new char[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = (char) ('1' + i);
        }
    }

    public void display() {
        System.out.println();
        System.out.println(" " + cells[0] + " | " + cells[1] + " | " + cells[2]);
        System.out.println("---+---+---");
        System.out.println(" " + cells[3] + " | " + cells[4] + " | " + cells[5]);
        System.out.println("---+---+---");
        System.out.println(" " + cells[6] + " | " + cells[7] + " | " + cells[8]);
        System.out.println();
    }

    public boolean isCellEmpty(int pos) {
        return cells[pos] != 'X' && cells[pos] != 'O';
    }

    public void makeMove(int pos, char player) {
        cells[pos] = player;
    }

    public boolean checkWin(char player) {
        // check rows
        for (int i = 0; i < 9; i += 3) {
            if (cells[i] == player && cells[i + 1] == player && cells[i + 2] == player) {
                return true;
            }
        }
        // check columns
        for (int i = 0; i < 3; i++) {
            if (cells[i] == player && cells[i + 3] == player && cells[i + 6] == player) {
                return true;
            }
        }
        // check diagonals
        if (cells[0] == player && cells[4] == player && cells[8] == player) {
            return true;
        }
        if (cells[2] == player && cells[4] == player && cells[6] == player) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (cells[i] != 'X' && cells[i] != 'O') {
                return false;
            }
        }
        return true;
    }
}

