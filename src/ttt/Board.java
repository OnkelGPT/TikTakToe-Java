package ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 3x3 TicTacToe board.
 */
public class Board {
    private final char[] cells = new char[9];

    /**
     * Constructs an empty board.
     */
    public Board() {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = ' ';
        }
    }

    /**
     * Copy constructor.
     */
    public Board(Board other) {
        System.arraycopy(other.cells, 0, cells, 0, cells.length);
    }

    /**
     * Places a mark on the board.
     * @param index cell index 0-8
     * @param mark 'X' or 'O'
     * @return true if move valid
     */
    public boolean placeMark(int index, char mark) {
        if (index < 0 || index >= cells.length || cells[index] != ' ') {
            return false;
        }
        cells[index] = mark;
        return true;
    }

    /**
     * Checks if a player has won the game.
     */
    public char checkWin() {
        int[][] wins = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] w : wins) {
            if (cells[w[0]] != ' ' && cells[w[0]] == cells[w[1]] && cells[w[1]] == cells[w[2]]) {
                return cells[w[0]];
            }
        }
        return ' ';
    }

    /**
     * Checks if the board is full.
     */
    public boolean isFull() {
        for (char c : cells) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Lists all empty cell indexes.
     */
    public List<Integer> availableMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == ' ') {
                moves.add(i);
            }
        }
        return moves;
    }

    public char getCell(int index) {
        return cells[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(cells[i]);
            if ((i + 1) % 3 == 0) {
                if (i < 8) sb.append("\n-----\n");
            } else {
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
