public class Board {


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
        // for columns 1-8
        for (int col = 0; col < 8; col++) {
            // for rows 1-2 (White set up)
            for (int row = 0; row < 2; row++) {
                // create and set white pawns
                if (row == 1) {
                     board[col][row].piece = new Pawn(true);
                }

                // row == 0 set rest of white pieces
                else {
                    if (col == 0 | col == 7) {
                        board[col][row].piece = new Rook(true);
                    } else if (col == 1 | col == 6) {
                        board[col][row].piece = new Horse(true);
                    } else if (col == 2 | col == 5) {
                        board[col][row].piece = new Bishop(true);
                    } else if (col == 3) {
                        board[col][row].piece = new Queen(true);
                    } else board[col][row].piece = new King(true);
                }
            }
            // black set up
            for (int row = 6; row < 8; row++) {
                if (row == 6) {
                    board[col][row].piece = new Pawn(false);
                } else {
                    if (col == 0 | col == 7) {
                        board[col][row].piece = new Rook(false);
                    } else if (col == 1 | col == 6) {
                        board[col][row].piece = new Horse(false);
                    } else if (col == 2 | col == 5) {
                        board[col][row].piece = new Bishop(false);
                    } else if (col == 3) {
                        board[col][row].piece = new Queen(false);
                    } else board[col][row].piece = new King(false);
                }
            }
        }
    }

    @Override
    public String toString() {
        String status = "  | a || b || c || d || e || f || g || h |\n  |--------------------------------------|";
        for (int row = 0; row < 8; row++){
            status += "\n" + (8 - row) + " ";
            for (int col = 0; col < 8; col++){
                status += "|" + board[col][row].piece.toString() + "|";
            }
        }
        return status;
    }

    public boolean takeTurn(boolean white, char col, int row, char destCol, int destRow) {
        // check if there is a piece at location
        if(!board[Character.valueOf(col) - 97][row - 1].piece.alive) {
            System.out.println("no piece at location");
            return false;
        }
        // check if correct colour
        else if (board[Character.valueOf(col) - 97][row - 1].piece.white == white) {
            System.out.println("incorrect colour");
            return false;
        }


        //check valid move
        // make move
        return true;
    }
}
