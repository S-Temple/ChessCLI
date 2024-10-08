/**
 * Main class for running the chess game.
 * <p>
 * This class contains the main game loop where players alternate turns between
 * white and black, inputting moves for their respective pieces. The game continues
 */
public class Main {

    /**
     * The main method where the chess game starts and is controlled.
     * <p>
     * This method sets up a {@code ChessCLI} object for input handling and a {@code Board} object to manage the game state.
     * The game alternates between white and black players, prompting for piece selection
     * and destination, validating the moves, and updating the board. The game runs indefinitely.
     *
     * @param args Command line arguments (not used in this game).
     */
    public static void main(String[] args) {

        // chessCLI object for input validation and system in operations
        ChessCLI input = new ChessCLI();

        // TODO add menu if needed... maybe two player or ai player game
        System.out.println("NEW GAME\n------------------------------");

        Board game = new Board();
        // print game initial board state
        System.out.println(game);

        //break on win, quit
        boolean successful = false;

        // Game loop/flow control
        while (true){
            successful = false;

            // white turn
            while(!successful) {
                input.print("WHITE move; select a piece\n");
                successful = game.takeTurn(true,
                        input.getUserChar("Please enter column of piece"),
                        input.getUserInt("Please enter row of piece"),
                        input.getUserChar("Please enter destination column"),
                        input.getUserInt("Please enter destination row"));

                if (!successful) input.print("Not valid move please try again\n\n");
            }

            System.out.println(game);
            successful = false;
            while(!successful) {
                input.print("BLACK move; select a piece\n");
                successful = game.takeTurn(false,
                        input.getUserChar("Please enter column of piece"),
                        input.getUserInt("Please enter row of piece"),
                        input.getUserChar("Please enter destination column"),
                        input.getUserInt("Please enter destination row"));

                if (!successful) input.print("Not valid move please try again\n\n");
            }
            System.out.println(game);
        }

    }
}