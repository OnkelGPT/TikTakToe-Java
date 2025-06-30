package ttt;

/**
 * Implements a minimax AI for TicTacToe.
 */
public class MinimaxAI {
    private final char aiMark;
    private final char humanMark;

    public MinimaxAI(char aiMark) {
        this.aiMark = aiMark;
        this.humanMark = (aiMark == 'X') ? 'O' : 'X';
    }

    /**
     * Returns the best move index for the AI.
     */
    public int chooseMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
        for (int move : board.availableMoves()) {
            Board next = new Board(board);
            next.placeMark(move, aiMark);
            int score = minimax(next, false);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, boolean isAiTurn) {
        char winner = board.checkWin();
        if (winner == aiMark) return 10;
        if (winner == humanMark) return -10;
        if (board.isFull()) return 0;

        int bestScore = isAiTurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int move : board.availableMoves()) {
            Board next = new Board(board);
            next.placeMark(move, isAiTurn ? aiMark : humanMark);
            int score = minimax(next, !isAiTurn);
            if (isAiTurn) {
                bestScore = Math.max(score, bestScore);
            } else {
                bestScore = Math.min(score, bestScore);
            }
        }
        return bestScore;
    }
}
