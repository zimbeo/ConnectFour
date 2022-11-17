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
        // Show the initialized 2d array by looping through and showing each val,
        // separate with pipe for cleanliness
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

    public static boolean playUserTurn(Scanner scanner, char[][] gameBoard, char userTokenColor) {
        
        // Get the row and column that the token should be input in
        //todo: Getting the user input for playedRow and playedColumn should have some error handling in case input is not an int, I'll come back to that
        System.out.println("Enter the row to place your token - (0-5) ");
        int playedRow = scanner.nextInt();

        System.out.println("Enter the column to place your token - (0-6) ");
        int playedColumn = scanner.nextInt();

        if (checkTurnValidity(playedRow, playedColumn, gameBoard)) {
            gameBoard[playedRow][playedColumn] = userTokenColor;
            showGameBoard(gameBoard);
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
        int rowUpperBound = 7;
        int columnUpperBound = 8;

        int randomRow = rand.nextInt(rowUpperBound);
        int randomColumn = rand.nextInt(columnUpperBound);

        if (checkTurnValidity(randomRow, randomColumn, gameBoard)) {
            gameBoard[randomRow][randomColumn] = compTokenColor;
            System.out.println("\nComputer played token in row " + randomRow + " and column " + randomColumn + "\n");
            showGameBoard(gameBoard);
        } else {
            System.out.println("ERROR: Your turn was invalid, try again.");
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
                
                if (playedTurn == true) {
                    turn = setTurn(turn);
                }
            }

            // This will be running the computer turn method in the future (haven't made it yet)
            if (turn == compTokenColor) {
                playComputerTurn(scanner, gameBoard, compTokenColor);
                turn = setTurn(turn);
            }
        }
    }
}
