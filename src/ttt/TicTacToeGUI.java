package ttt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple Swing GUI for playing TicTacToe against the minimax AI.
 */
public class TicTacToeGUI extends JFrame {
    private final JButton[] buttons = new JButton[9];
    private Board board = new Board();
    private final char humanMark = 'X';
    private final char aiMark = 'O';
    private final MinimaxAI ai = new MinimaxAI(aiMark);

    public TicTacToeGUI() {
        super("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeBoard();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton(" ");
            final int index = i;
            btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handlePlayerMove(index);
                }
            });
            buttons[i] = btn;
            add(btn);
        }
    }

    private void handlePlayerMove(int index) {
        if (board.getCell(index) != ' ' || board.checkWin() != ' ') return;
        board.placeMark(index, humanMark);
        buttons[index].setText(String.valueOf(humanMark));
        checkGameEnd();
        if (board.checkWin() == ' ' && !board.isFull()) {
            int move = ai.chooseMove(board);
            board.placeMark(move, aiMark);
            buttons[move].setText(String.valueOf(aiMark));
            checkGameEnd();
        }
    }

    private void checkGameEnd() {
        char winner = board.checkWin();
        if (winner != ' ' || board.isFull()) {
            String message;
            if (winner == humanMark) {
                message = "You win!";
            } else if (winner == aiMark) {
                message = "Computer wins!";
            } else {
                message = "Draw!";
            }
            JOptionPane.showMessageDialog(this, message);
            resetGame();
        }
    }

    private void resetGame() {
        board = new Board();
        for (int i = 0; i < 9; i++) {
            buttons[i].setText(" ");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeGUI().setVisible(true);
            }
        });
    }
}
