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

    // marks used by both game modes
    private final char player1Mark = 'X';
    private final char player2Mark = 'O';

    private final MinimaxAI ai = new MinimaxAI(player2Mark);

    /** true if the game is in two player mode */
    private final boolean twoPlayer;

    private char currentMark = player1Mark;

    public TicTacToeGUI(boolean twoPlayer) {
        super("TicTacToe");
        this.twoPlayer = twoPlayer;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeBoard();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton("");
            final int index = i;
            btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            btn.setPreferredSize(new Dimension(120, 120));
            btn.setFocusPainted(false);
            btn.setMargin(new Insets(0, 0, 0, 0));
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

        board.placeMark(index, currentMark);
        buttons[index].setText(String.valueOf(currentMark));
        checkGameEnd();

        if (!twoPlayer && board.checkWin() == ' ' && !board.isFull()) {
            int move = ai.chooseMove(board);
            board.placeMark(move, player2Mark);
            buttons[move].setText(String.valueOf(player2Mark));
            checkGameEnd();
        } else {
            currentMark = (currentMark == player1Mark) ? player2Mark : player1Mark;
        }
    }

    private void checkGameEnd() {
        char winner = board.checkWin();
        if (winner != ' ' || board.isFull()) {
            String message;
            if (winner == ' ') {
                message = "Draw!";
            } else if (twoPlayer) {
                message = "Player " + winner + " wins!";
            } else if (winner == player1Mark) {
                message = "You win!";
            } else {
                message = "Computer wins!";
            }
            JOptionPane.showMessageDialog(this, message);
            resetGame();
        }
    }

    private void resetGame() {
        board = new Board();
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        currentMark = player1Mark;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String[] options = {"Two Players", "Vs Computer"};
                int choice = JOptionPane.showOptionDialog(null,
                        "Choose game mode",
                        "TicTacToe",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
                boolean twoPlayer = choice == 0;
                new TicTacToeGUI(twoPlayer).setVisible(true);
            }
        });
    }
}
