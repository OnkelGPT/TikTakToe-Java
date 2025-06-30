# TikTakToe-Java

This project provides a simple command line TicTacToe game written in Java. It features a computer opponent that uses the minimax algorithm, making it hard to beat.

## Compile and Run

Use `javac` to compile the sources and `java` to run the game:

```bash
# compile
test -d out || mkdir out
javac -d out src/ttt/*.java

# run
java -cp out ttt.TicTacToe
```

The game prints the board with numbers 1-9 for empty cells. Enter the number of the cell where you want to place your `X`.
