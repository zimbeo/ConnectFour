public class ConnectFour {
    public static void main(String[] args) {

        // Initialize the game board 2d array with 6 rows + 7 columns
        char[][] gameBoard = new char[6][7];

        // Go through each row and corresponding column to make each index a blank entry to start with
        //todo: needs to be pulled out of the method to show the game board long term
        for (int i = 0; i < gameBoard.length; ++i) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = ' ';
            }
        }

        // Show the initialized 2d array by looping through and showing each val, separate with pipe for cleanliness
        System.out.println("   1 2 3 4 5 6 7\n  ---------------");

        for (int i = 0; i < gameBoard.length; i++){

            System.out.print((i + 1) + " |");

            for (int j = 0; j < gameBoard[0].length; j++){
                System.out.print(gameBoard[i][j] + "|");
            }

            System.out.println("\n  ---------------");
        }
        System.out.println("   1 2 3 4 5 6 7 ");
    }
}
