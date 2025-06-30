package ttt;

import java.util.Scanner;

/**
 * Command line TicTacToe against a minimax AI.
 */
public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        char human = 'X';
        char ai = 'O';
        MinimaxAI opponent = new MinimaxAI(ai);

        System.out.println("Play TicTacToe! You are X and computer is O.");
        while (true) {
            printBoard(board);
            if (board.checkWin() == human) {
                System.out.println("You win!");
                break;
            }
            if (board.checkWin() == ai) {
                System.out.println("Computer wins!");
                break;
            }
            if (board.isFull()) {
                System.out.println("Draw!");
                break;
            }

            if (board.availableMoves().size() % 2 == 1) { // human's turn
                int move = -1;
                while (move < 0 || !board.placeMark(move, human)) {
                    System.out.print("Choose your move (1-9): ");
                    String input = scanner.nextLine();
                    try {
                        move = Integer.parseInt(input) - 1;
                    } catch (NumberFormatException e) {
                        move = -1;
                    }
                    if (!board.placeMark(move, human)) {
                        System.out.println("Invalid move.");
                    }
                }
            } else {
                int aiMove = opponent.chooseMove(board);
                board.placeMark(aiMove, ai);
                System.out.println("Computer chooses " + (aiMove + 1));
            }
        }
        scanner.close();
        printBoard(board);
    }

    private static void printBoard(Board board) {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            char c = board.getCell(i);
            if (c == ' ') {
                System.out.print(i + 1);
            } else {
                System.out.print(c);
            }
            if ((i + 1) % 3 == 0) {
                if (i < 8) System.out.println("\n-----");
            } else {
                System.out.print("|");
            }
        }
        System.out.println("\n");
    }
}
