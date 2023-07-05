import java.util.StringJoiner;

public class Board {
/*
  | a || b || c || d || e || f || g || h |
  |--------------------------------------|
7 |B R||B H||B B||B Q||B K||B B||B H||B R|
6 |B P||B P||B P||B P||B P||B P||B P||B P|
5 |   ||   ||   ||   ||   ||   ||   ||   |
4 |   ||   ||   ||   ||   ||   ||   ||   |
3 |   ||   ||   ||   ||   ||   ||   ||   |
2 |   ||   ||   ||   ||   ||   ||   ||   |
1 |W P||W P||W P||W P||W P||W P||W P||W P|
0 |W R||W H||W B||W Q||W K||W B||W H||W R|
*/

    Location[][] board;

    Board() {

        //create board
        board = new Location[8][8];

        // for ASCII chars a-h
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                board[col][row] = new Location((char) (col + 97), row + 1);
            }
        }

        this.resetBoard();
    }


    public void resetBoard() {
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
        StringJoiner status = new StringJoiner("\n");
        status.add("  |a ||b ||c ||d ||e ||f ||g ||h |");
        status.add("  |------------------------------|");
        for (int row = 7; row >= 0; row--) {
            StringBuilder rowString = new StringBuilder();
            rowString.append(row + 1).append(" ");
            for (int col = 0; col < 8; col++) {
                rowString.append("|").append(board[col][row].piece.toString()).append("|");
            }
            status.add(rowString.toString());
        }
        return status.toString();
    }

    public boolean takeTurn(boolean white, char col, int row, char destCol, int destRow) {
        // check if there is a piece at location
        Piece selected = board[col - 97][row - 1].piece;
        if (!selected.alive) {
            System.out.println("no piece at location");
            return false;
        }
        // check if correct colour
        else if (selected.white != white) {
            System.out.println("incorrect colour");
            return false;
        }

        // no need to check if dest on board as CLI take care of user input.

        // piece can reach?
        if (!board[col - 97][row - 1].validMove(destCol, destRow)) {
            System.out.println("piece cannot reach location");
            return false;
        }

        // TODO: check if other pieces are between destination and start return false if so
        // check up or down rook/queen
        if (destCol - col == 0) {

            int moves = destRow - row;

            // moving down
            if (moves < 0) {
                for (int i = -1; i > moves; i--) {
                    if (board[col - 97][row + i - 1].piece.alive) {
                        System.out.println("Invalid move other piece(s) in the way");
                        return false;
                    }
                }
                // moving up
            } else {
                for (int i = 1; i < moves; i++) {
                    if (board[col - 97][row + i - 1].piece.alive) {
                        System.out.println("Invalid move other piece(s) in the way");
                        return false;
                    }
                }
            }
        }

        // check right or left queen/rook
        if (destRow - row == 0) {
            int moves = destCol - col;

            // moving left
            if (moves < 0) {
                for (int i = -1; i > moves; i--) {
                    if (board[col + i - 97][row - 1].piece.alive) {
                        System.out.println("Invalid move other piece(s) in the way");
                        return false;
                    }
                }
                // moving right
            } else {
                for (int i = 1; i < moves; i++) {
                    if (board[col + i - 97][row - 1].piece.alive) {
                        System.out.println("Invalid move other piece(s) in the way");
                        return false;
                    }
                }
            }
        }

        // check diagonal bishop/queen
        if (Math.abs(destCol - col) == Math.abs(destRow - row)) {
            // maybe way for two for loops where instead of ++/-- use (dest-source/(dest-source))
            int colMovement = (destCol - col);
            int colIncrementer = colMovement / Math.abs(colMovement);
            colMovement = Math.abs(colMovement);

            int rowIncrementer = (destRow - row);
            rowIncrementer = rowIncrementer / Math.abs(rowIncrementer);

            for (int i = colIncrementer, j = rowIncrementer; Math.abs(i) < Math.abs(colMovement); i += colIncrementer, j += rowIncrementer) {
                if (board[col + i - 97][row + j - 1].piece.alive) {
                    System.out.println("Invalid move other piece(s) in the way");
                    return false;
                }
            }
        }

        // check if piece at locDest
        if (board[destCol - 97][destRow - 1].piece.alive) {
            // is piece other colour?
            if (board[destCol - 97][destRow - 1].piece.white == white) {
                System.out.println("Same Team!");
                return false;
            }
        }


        if (board[destCol - 97][destRow - 1].piece.alive) {
            if (board[destCol - 97][destRow - 1].piece instanceof King) {
                if (white) System.out.println("WHITE WINS");
                else System.out.println("BLACK WINS");
                System.exit(0);
            }
            board[destCol - 97][destRow - 1].piece = new Piece();
        }
        board[col - 97][row - 1].piece = new Piece();
        board[destCol - 97][destRow - 1].piece = selected;
        return true;
    }
}
