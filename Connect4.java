import java.util.Objects;
import java.util.Scanner;

public class Connect4 {

    String[][] board;
    boolean winner;
    boolean draw;
    int winningPlayer;
    int playerTurn;

    public Connect4() {
        winningPlayer = 0;
        draw = false;
        playerTurn = 1;
        board = new String[6][7];
        initializeBoard();
        displayBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = " - ";
            }
        }
    }

    private void displayBoard() {
        System.out.println(" ");
        System.out.println(" *** Connect 4 *** ");
        System.out.println(" ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean validInput(String input) {
        return (Objects.equals(input, "1") || Objects.equals(input, "2") || Objects.equals(input, "3") ||
                Objects.equals(input, "4") || Objects.equals(input, "5") || Objects.equals(input, "6") ||
                Objects.equals(input, "7"));
    }

    private boolean isColumnFull(int column) {
        return (!Objects.equals(board[0][column - 1], " - "));
    }

    private int getNextAvailableSlot(int column) {
        int position = 5;
        while (position >= 0) {
            if (!Objects.equals(board[position][column], " - ")) {
                position--;
            } else {
                break;
            }
        }
        return position;
    }

    private void swapPlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (Objects.equals(board[i][j], " - ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private String checkForWinner() {
        // Implement the logic to check for a winner
        // This includes horizontal, vertical, and diagonal checks
        // Return "X" if Player 1 wins, "O" if Player 2 wins, or null if no winner yet
        // You may use the methods checkVerticalWinner(), checkHorizontalWinner(), etc., here
        return null;
    }

    private void placePiece() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player " + playerTurn + ", please enter a column number (1-7):");
        String input = sc.nextLine();

        while (!validInput(input) || isColumnFull(Integer.parseInt(input))) {
            if (!validInput(input)) {
                System.out.println("Invalid input. Please enter a valid column number (1-7):");
            } else {
                System.out.println("Column is full. Please choose another column:");
            }
            input = sc.nextLine();
        }
        int columnChoice = Integer.parseInt(input) - 1;

        int availableSlot = getNextAvailableSlot(columnChoice);

        System.out.println("Next available row in column: " + availableSlot);
        String pieceToPlace = (playerTurn == 1) ? " X " : " O ";
        board[availableSlot][columnChoice] = pieceToPlace;
        displayBoard();
        swapPlayerTurn();
    }

    private void showWinner(String symbol) {
        if (Objects.equals(symbol, " X ")) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }
    }

    public void playGame() {
        while (winningPlayer == 0) {
            winningPlayer = checkForWinner() != null ? (checkForWinner().equals(" X ") ? 1 : 2) : 0;
            draw = isBoardFull() && winningPlayer == 0;
            if (winningPlayer != 0 || draw) {
                if (winningPlayer != 0) {
                    showWinner(checkForWinner());
                } else {
                    System.out.println("It's a draw!");
                }
                break;
            }
            placePiece();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Connect 4");
        Connect4 c4 = new Connect4();
        c4.playGame();
    }
}
