public class Main {
    public static void main(String[] args) {
        ChessCLI input = new ChessCLI();
        System.out.println("Play game?");
        // print instructions
        Board game = new Board();
        System.out.println(game);
        //break on win, quit
        boolean successful = false;
        while (true){
            successful = false;
            // white turn
            while(!successful) {
                input.print("WHITE move; select a piece\n");
                successful = game.takeTurn(true, input.getUserChar("Please enter column of piece"), input.getUserInt("Please enter row of piece"),
                                            input.getUserChar("Please enter destination column"), input.getUserInt("Please enter destination row"));
                if (!successful) input.print("Not valid please try again\n\n");
            }

            System.out.println(game);
            successful = false;
            while(!successful) {
                input.print("BLACK move; select a piece\n");
                successful = game.takeTurn(false, input.getUserChar("Please enter column of piece"), input.getUserInt("Please enter row of piece"),
                        input.getUserChar("Please enter destination column"), input.getUserInt("Please enter destination row"));
                if (!successful) input.print("Not valid please try again\n\n");
            }
            System.out.println(game);
        }

    }
}