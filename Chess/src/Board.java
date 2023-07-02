public class Board {

    // checks for kill
    // monitor turns
    // print board status?
    // maybe spots?
    Location[][] board;

    Board() {

        //create board
        board = new Location[8][8];

        // for ASCII chars a-h
        for (int col = 0; col < 8; col++){
            for (int row = 0; row < 8; row++){
                board[col][row] = new Location((char) (col + 97), row + 1);
            }
        }

        this.resetBoard();
    }


    public void resetBoard(){
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 2; row++) {
                if (row == 1) {
                    board[col][row].piece = new Pawn(true);
                } else if (row == 6) {
                    board[col][row].piece = new Pawn(true);
                }


            }
        }
    }
    @Override
    public String toString() {
        return "stuff";
    }
}
