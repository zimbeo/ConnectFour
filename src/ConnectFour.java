import java.util.Scanner;
import java.util.Random;

public class ConnectFour {

    public static char[][] initializeGameBoard() {

        // Initialize the game board 2d array with 6 rows + 7 columns
        char[][] gameBoard = new char[6][7];

        // Go through each row and corresponding column to make each index a blank entry to start
        for (int i = 0; i < gameBoard.length; ++i) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }

        return gameBoard;
    }

    public static void showGameBoard(char[][] gameBoard) {

        // Show the initialized 2d array by looping through and showing each value
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

    public static boolean checkTurnValidity(int column, char[][] gameBoard) {

        // Check if column fits dimensions of the game board
        if (column > gameBoard.length || column < 0) {
            return false;
        }

        return true;
    }

    public static boolean playUserTurn(Scanner scanner, char[][] gameBoard, char userTokenColor) {

        // Get the column that the token should be input in
        System.out.println("Enter the column to place your token - (0-6) ");
        int playedColumn = scanner.nextInt();

        if (checkTurnValidity(playedColumn, gameBoard)) {

            // If the turn was valid, loop through board, and decrement row as necessary
            for (int row = gameBoard.length - 1; row >= 0; row--){
                if(gameBoard[row][playedColumn] == ' '){
                    gameBoard[row][playedColumn] = userTokenColor;
                    break;
                }
            }

            showGameBoard(gameBoard);

            // Return true so main method knows turn was successfully played
            return true;
        } else {
            System.out.println("ERROR: Your turn was invalid, try again.");
            return false;
        }
    }

    public static char setTurn(char turn) {
        char newTurn;

        if (turn == 'R') {
            newTurn = 'Y';
        } else {
            newTurn = 'R';
        }

        return newTurn;
    }

    public static void playComputerTurn(Scanner scanner, char[][] gameBoard, char compTokenColor) {

        Random rand = new Random();

        // Generate a random column that the computer will play, set bound to 8 as there is 7 columns
        int columnUpperBound = 8;
        int randomColumn = rand.nextInt(columnUpperBound);

        if (checkTurnValidity(randomColumn, gameBoard)) {

            // If the turn was valid, loop through board, and decrement row as necessary
            for (int row = gameBoard.length - 1; row >= 0; row--){
                if(gameBoard[row][randomColumn] == ' '){
                    gameBoard[row][randomColumn] = compTokenColor;
                    System.out.println("\nComputer played token in row " + row + " and column " + randomColumn + "\n");
                    break;
                }
            }

            showGameBoard(gameBoard);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char turn = 'R';
        char compTokenColor = 'Y';
        char userTokenColor = 'R';
        boolean playedTurn;

        // Create and show the board for the game
        char[][] gameBoard = initializeGameBoard();
        showGameBoard(gameBoard);

        while (true) {
            if (turn == userTokenColor) {
                playedTurn = playUserTurn(scanner, gameBoard, turn);

                // Validates if the play was actually successful
                if (playedTurn) {
                    turn = setTurn(turn);
                }
            }

            if (turn == compTokenColor) {
                playComputerTurn(scanner, gameBoard, compTokenColor);

                // Assume the computer played a valid turn since it plays a random number with an upper bound
                turn = setTurn(turn);
            }
        }
    }
}
