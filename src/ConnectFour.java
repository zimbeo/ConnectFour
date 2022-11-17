import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFour {

    public static char[][] initializeGameBoard() {
        // Initialize the game board 2d array with 6 rows + 7 columns
        char[][] gameBoard = new char[6][7];

        // Go through each row and corresponding column to make each index a blank entry to start with
        for (int i = 0; i < gameBoard.length; ++i) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }

        return gameBoard;
    }

    public static void showGameBoard(char[][] gameBoard) {
        // Show the initialized 2d array by looping through and showing each val, separate with pipe for cleanliness
        System.out.println("   0 1 2 3 4 5 6\n  ---------------");

        for (int i = 0; i < gameBoard.length; i++) {

            System.out.print(i + "|");

            for (int j = 0; j < gameBoard[0].length; j++) {
                System.out.print(gameBoard[i][j] + "|");
            }

            System.out.println("\n  ---------------");
        }
        System.out.println("   0 1 2 3 4 5 6 ");
    }

    public static boolean checkTurnValidity(int row, int column, char[][] gameBoard) {

        // Check if column fits dimension for columns
        if (column > gameBoard.length - 1 || column < 0) {
            return false;
        }

        if (row > gameBoard[0].length - 1 || row < 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char playerTurn = 'R';
        char robotTokenColor = 'Y';
        char userTokenColor = 'R';

        // Create the board for the game
        char[][] gameBoard = initializeGameBoard();

        //Show the game board
        showGameBoard(gameBoard);

        while (playerTurn == 'R') {
            try {
                // Get the row and column that the token should be input in
                System.out.println("Enter the row to place your token - (0-5) ");
                int playedRow = scanner.nextInt();

                System.out.println("Enter the column to place your token - (0-6) ");
                int playedColumn = scanner.nextInt();

                if (checkTurnValidity(playedRow, playedColumn, gameBoard)) {
                    gameBoard[playedRow][playedColumn] = userTokenColor;
                    showGameBoard(gameBoard);

                    // Change it to the computers token
                    playerTurn = 'Y';
                } else {
                    System.out.println("ERROR: Your turn was invalid, try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("ERROR: You must input an integer value!");
            }
        }
    }
}
