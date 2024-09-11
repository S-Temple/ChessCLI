import java.util.StringJoiner;

/**
 * Manages state of board
 * All movement has to be validated on a board level then a piece level before actually changing state
 * For example a board level check is to see if location is a valid area of board and
 * a piece level check is to see if pawn is moving more then 1 space
 *
 * Provides methods for printing state and changing state
 */
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
    Piece[][] board;

    /**
     * Constructor creates array of 8 arrays of 8 Locations
     * Uses reset board to populate pieces
     */
    Board() {

        // create board
        board = new Piece[8][8];

        // for ASCII chars a-h
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                board[col][row] = new Piece();
            }
        }

        this.setBoard();
    }

    /**
     * Populates board with starting positions of pieces
     */
    public void setBoard() {
        // for columns 1-8
        for (int col = 0; col < 8; col++) {
            // for rows 1-2 (White set up)
            for (int row = 0; row < 2; row++) {
                // create and set white pawns
                if (row == 1) {
                    board[col][row] = new Pawn( true);
                }

                // row == 0 set rest of white pieces
                else {
                    if (col == 0 | col == 7) {
                        board[col][row] = new Rook(true);
                    } else if (col == 1 | col == 6) {
                        board[col][row] = new Horse(true);
                    } else if (col == 2 | col == 5) {
                        board[col][row] = new Bishop(true);
                    } else if (col == 3) {
                        board[col][row] = new Queen(true);
                    } else board[col][row] = new King(true);
                }
            }

            // black set up
            for (int row = 6; row < 8; row++) {
                if (row == 6) {
                    board[col][row] = new Pawn(false);
                } else {
                    if (col == 0 | col == 7) {
                        board[col][row] = new Rook(false);
                    } else if (col == 1 | col == 6) {
                        board[col][row] = new Horse(false);
                    } else if (col == 2 | col == 5) {
                        board[col][row] = new Bishop(false);
                    } else if (col == 3) {
                        board[col][row] = new Queen(false);
                    } else board[col][row] = new King(false);
                }
            }
        }
    }

    /**
     * Formats board state into a printable string
     * @return      String representing the board state
     */
    @Override
    public String toString() {
        StringJoiner status = new StringJoiner("\n");
        status.add("  |a ||b ||c ||d ||e ||f ||g ||h |");
        status.add("  |------------------------------|");
        for (int row = 7; row >= 0; row--) {
            StringBuilder rowString = new StringBuilder();
            rowString.append(row + 1).append(" ");
            for (int col = 0; col < 8; col++) {
                rowString.append("|").append(board[col][row].toString()).append("|");
            }
            status.add(rowString.toString());
        }
        return status.toString();
    }

    /**
     * Logic for attempting to move a chess piece
     * mostly validation if move is legal withing rules of chess
     *
     * @param  white boolean value for what player color if currently attempting a turn
     * @param  col the char representing the column a piece for selection is in
     * @param  row the int representing the row a piece for selection is in
     * @param  destCol the char representing the col location the selected piece is attempting to move to
     * @param  destRow the int representing the row location the selected piece is attempting to move to
     * @return      boolean value representing if the attempted move was successful or not
     */
    public boolean takeTurn(boolean white, char col, int row, char destCol, int destRow) {
        // check if there is an alive piece at selected location
        int colIndex = col-97;
        int destColIndex = destCol-97;
        int rowIndex = row-1;
        int destRowIndex = destRow-1;

        Piece selected = board[colIndex][rowIndex];
        if (!selected.alive) {
            System.out.println("no piece at location");
            return false;
        }
        // check if correct colour
        else if (selected.white != white) {
            System.out.println("incorrect colour");
            return false;
        }

        // no need to check if dest on board as ChessCLI validates all user input.
        // TODO: maybe add bounds checks anyway?

        // check if valid move for selected piece type
        if (!selected.validMove(col,row,destCol, destRow)) {
            System.out.println("piece cannot reach location");
            return false;
        }

        // collision checks
        int vertical = destRowIndex - rowIndex;
        int horizontal = destColIndex - colIndex;

        switch (selected.getClass().getName()) {
            case "Horse", "King" -> {} // not possible to collide without attacking piece
            case "Pawn" -> {
                if(selected.white){
                    // white increasing vertical val
                    if(horizontal == 0) { // not a attack
                        for(int i = 1; i <= vertical; i++){
                            if(board[colIndex][rowIndex+i].alive) {
                                System.out.println("piece blocking path");
                                return false;
                            }
                        }
                    } else { // attack move
                        if(!board[destColIndex][destRowIndex].alive) {
                            System.out.println("no piece for pawn to attack at location");
                            return false;
                        }
                    }
                } else {
                    // negative vertical movement
                    if(horizontal == 0) { // not a attack
                        for(int i = -1; i >= vertical; i--){
                            if(board[colIndex][rowIndex+i].alive) {
                                System.out.println("piece blocking path");
                                return false;
                            }
                        }
                    } else { // attack move
                        if(!board[destColIndex][destRowIndex].alive) {
                            System.out.println("no piece for pawn to attack at location");
                            return false;
                        }
                    }
                }
            }

            case "Rook" -> {
                if(vertical < 0){ // moving down
                    for (int i = 1; i < Math.abs(vertical); i++){
                        if(board[colIndex][rowIndex-i].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if(vertical > 0){ // moving up
                    for (int i = 1; i < vertical; i++){
                        if(board[colIndex][rowIndex+i].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if (horizontal < 0) { // moving left
                    for (int i = 1; i < Math.abs(horizontal); i++){
                        if(board[colIndex-i][rowIndex].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if (horizontal > 0) {
                    for (int i = 1; i < horizontal; i++){
                        if(board[colIndex+i][rowIndex].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }

                } else {
                    System.out.println("No movement for rook");
                    return false;
                }
            }

            case "Bishop" -> {
                if(Math.abs(vertical)==Math.abs(horizontal)){
                    int vertIncrement = vertical/Math.abs(vertical);
                    int horIncrement = horizontal/Math.abs(horizontal);
                    for (int vertVal = vertIncrement, horVal =horIncrement; vertVal != vertical && horVal != horizontal; vertVal += vertIncrement, horVal += horIncrement){
                        if (board[colIndex+horVal][rowIndex+vertVal].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }


                } else {
                    System.out.println("Invalid move");
                    return false;
                }
            }

            // TODO: Extract diagonal and rook tests to private member methods
            case "Queen" -> {
                if(Math.abs(vertical)==Math.abs(horizontal)){
                    int vertIncrement = vertical/Math.abs(vertical);
                    int horIncrement = horizontal/Math.abs(horizontal);
                    for (int vertVal = vertIncrement, horVal =horIncrement; vertVal != vertical && horVal != horizontal; vertVal += vertIncrement, horVal += horIncrement){
                        if (board[colIndex+horVal][rowIndex+vertVal].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if(vertical < 0){ // moving down
                    for (int i = 1; i < Math.abs(vertical); i++){
                        if(board[colIndex][rowIndex-i].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if(vertical > 0){ // moving up
                    for (int i = 1; i < vertical; i++){
                        if(board[colIndex][rowIndex+i].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if (horizontal < 0) { // moving left
                    for (int i = 1; i < Math.abs(horizontal); i++){
                        if(board[colIndex-i][rowIndex].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }
                } else if (horizontal > 0) { // moving right
                    for (int i = 1; i < horizontal; i++){
                        if(board[colIndex+i][rowIndex].alive) {
                            System.out.println("piece blocking path");
                            return false;
                        }
                    }

                } else {
                    System.out.println("No movement for Queen");
                    return false;
                }
            }
            default ->{
                System.out.println("Selected unexpected class type");
                return false;
            }
        }

        // check if piece at locDest
        if (board[destColIndex][destRowIndex].alive) {
            // is piece enemy colour?
            if (board[destCol - 97][destRow - 1].white == white) {
                System.out.println("Same Team!");
                return false;
            }
        }

        // Check win condition (King Killed)
        if (board[destColIndex][destRowIndex].alive) {
            if (board[destColIndex][destRowIndex] instanceof King) {
                if (white) System.out.println("WHITE WINS");
                else System.out.println("BLACK WINS");
                // TODO: return to menu?
                System.exit(0);
            }
        }

        // empty current location and place selected at destination
        board[colIndex][rowIndex] = new Piece();
        board[destColIndex][destRowIndex] = selected;
        return true;
    }
}
