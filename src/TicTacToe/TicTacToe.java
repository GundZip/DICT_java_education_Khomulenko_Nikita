import java.util.Scanner;

public class TicTacToe {
    private static char[] board = { '-', '-', '-',
            '-', '-', '-',
            '-', '-', '-' };
    private static char currentPlayer = 'X';
    private static char winner = '\0';
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            printBoard(board);
            if (winner != '\0') {
                break;
            }
            playerInput(board);
            checkWin(board);
            checkTie(board);
            switchPlayer();
        }

        if (winner == '\0') {
            System.out.println("It's a draw!");
        } else {
            System.out.println(winner + " wins!");
        }
    }

    public static void printBoard(char[] board) {
        System.out.println("=========");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
        }
        System.out.println("=========");
    }

    public static void playerInput(char[] board) {
        int inp;
        while (true) {
            if (currentPlayer == 'X') {
                System.out.print("Player (X): Enter a number (1-9): ");
            } else {
                System.out.print("Player (O): Enter a number (1-9): ");
            }
            inp = new Scanner(System.in).nextInt();
            if (inp >= 1 && inp <= 9 && board[inp - 1] == '-') {
                board[inp - 1] = currentPlayer;
                break;
            } else {
                System.out.println("Invalid input or cell already occupied! Try again.");
            }
        }
    }

    public static void checkWin(char[] board) {
        if (checkDiagonal(board) || checkHorizontal(board) || checkVertical(board)) {
            winner = currentPlayer;
        }
    }

    public static boolean checkHorizontal(char[] board) {
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == currentPlayer && board[i + 1] == currentPlayer && board[i + 2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkVertical(char[] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i] == currentPlayer && board[i + 3] == currentPlayer && board[i + 6] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonal(char[] board) {
        if ((board[0] == currentPlayer && board[4] == currentPlayer && board[8] == currentPlayer) ||
                (board[2] == currentPlayer && board[4] == currentPlayer && board[6] == currentPlayer)) {
            return true;
        }
        return false;
    }

    public static void checkTie(char[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                return;
            }
        }
        gameRunning = false;
    }

    public static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}
