import java.util.StringJoiner;

/**
 * Manages the state of the chessboard.
 * <p>
 * The board manages the movement of pieces by validating moves at both the board and piece level.
 * It ensures moves are within the boundaries of the board and adhere to the rules for each piece type.
 * It also provides methods to initialize the board, print its current state, and attempt moves.
 *
 * EXAMPLE: initial state printed to terminal
 * First character represents Black 'B' or White 'W'
 * Second character represents the type of piece Pawn, Rook, Bishop, Horse(Knight), King, and Queen
 *   | a || b || c || d || e || f || g || h |
 *   |--------------------------------------|
 * 7 |B R||B H||B B||B Q||B K||B B||B H||B R|
 * 6 |B P||B P||B P||B P||B P||B P||B P||B P|
 * 5 |   ||   ||   ||   ||   ||   ||   ||   |
 * 4 |   ||   ||   ||   ||   ||   ||   ||   |
 * 3 |   ||   ||   ||   ||   ||   ||   ||   |
 * 2 |   ||   ||   ||   ||   ||   ||   ||   |
 * 1 |W P||W P||W P||W P||W P||W P||W P||W P|
 * 0 |W R||W H||W B||W Q||W K||W B||W H||W R|
 */
public class Board {

    // 8x8 chess board represented as a 2D array of Piece objects
    Piece[][] board;

    /**
     * Constructor initializes an 8x8 board and populates it with pieces in their starting positions.
     * Calls {@link #setBoard()} to set up the initial state of the game.
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
     * Populates the board with the initial chess pieces in their starting positions.
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
     * Formats the current state of the board into a string for printing.
     * <p>
     * The board is represented as a grid, where rows and columns are labeled,
     * and each cell contains a chess piece or is empty.
     *
     * @return A string representing the board state.
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
     * Attempts to move a piece on the board, checking if the move is valid according to the rules of chess.
     * <p>
     * It first validates that the piece exists, is the correct color, and that the destination is valid.
     * Then, it checks for collisions and special movement rules (e.g., for pawns and knights).
     * If the move is valid, the piece is moved, and the method returns {@code true}.
     *
     * @param white    A boolean indicating whether it's white's turn.
     * @param col      The character representing the current column of the piece.
     * @param row      The integer representing the current row of the piece.
     * @param destCol  The character representing the destination column.
     * @param destRow  The integer representing the destination row.
     * @return {@code true} if the move was successful, {@code false} otherwise.
     */
    public boolean takeTurn(boolean white, char col, int row, char destCol, int destRow) {
        // Convert char columns 'a'-'h' to indices 0-7
        int colIndex = col-97;
        int destColIndex = destCol-97;
        int rowIndex = row-1;
        int destRowIndex = destRow-1;

        Piece selected = board[colIndex][rowIndex];

        // Check if there's an active piece at the selected location
        if (!selected.alive) {
            System.out.println("no piece at location");
            return false;
        }

        // Check if it's the correct color's turn
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


        // Handle collision checks based on piece type
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
                if (!goodOrthogonalMove(colIndex,rowIndex,vertical,horizontal)) {
                    System.out.println("No movement for rook");
                    return false;
                }
            }

            case "Bishop" -> {
                if(!goodDiagonalMove(colIndex,rowIndex,vertical,horizontal)) {
                        System.out.println("No movement for Bishop");
                        return false;

                }
            }

            case "Queen" -> {
                if(Math.abs(vertical)==Math.abs(horizontal)) {
                    if(!goodDiagonalMove(colIndex,rowIndex,vertical,horizontal)) {
                        System.out.println("No movement for Queen");
                        return false;
                    }
                } else {
                    if (!goodOrthogonalMove(colIndex, rowIndex, vertical, horizontal)) {
                        System.out.println("No movement for Queen");
                        return false;
                    }
                }
            }
            default ->{
                System.out.println("Selected unexpected class type");
                return false;
            }
        }

        // check if a piece exists at destination location
        if (board[destColIndex][destRowIndex].alive) {
            // is friendly fire?
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

        // handle special case pawn first move
        if (selected instanceof Pawn && ((Pawn) selected).firstMove) {
            ((Pawn) selected).removeFirstMove();
        }
        return true;
    }

    /**
     * Checks for collisions when attempting a diagonal move on the board.
     * This method verifies if any pieces are blocking the path between the
     * current position and the destination position for pieces like the
     * Bishop and Queen that move diagonally.
     *
     * @param colIndex   The column index of the current piece.
     * @param rowIndex   The row index of the current piece.
     * @param vertical   The vertical displacement (difference in rows) between the current position and the destination.
     * @param horizontal The horizontal displacement (difference in columns) between the current position and the destination.
     * @return           {@code true} if the path is clear for the diagonal move, {@code false} if a piece is blocking the path.
     */
    private boolean goodDiagonalMove(int colIndex, int rowIndex, int vertical, int horizontal) {
        int vertIncrement = vertical/Math.abs(vertical);
        int horIncrement = horizontal/Math.abs(horizontal);
        for (int vertOffset = vertIncrement, horizOffset = horIncrement;
             vertOffset != vertical && horizOffset != horizontal;
             vertOffset += vertIncrement, horizOffset += horIncrement) {
            if (board[colIndex+horizOffset][rowIndex+vertOffset].alive) {
                System.out.println("piece blocking path");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for collisions when attempting an orthogonal (horizontal or vertical) move on the board.
     * This method verifies if any pieces are blocking the path between the current position and the
     * destination position for pieces like the Rook and Queen that move horizontally or vertically.
     *
     * @param colIndex   The column index of the current piece.
     * @param rowIndex   The row index of the current piece.
     * @param vertical   The vertical displacement (difference in rows) between the current position and the destination.
     * @param horizontal The horizontal displacement (difference in columns) between the current position and the destination.
     * @return           {@code true} if the path is clear for the orthogonal move, {@code false} if a piece is blocking the path.
     */
    private boolean goodOrthogonalMove(int colIndex, int rowIndex, int vertical, int horizontal) {
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
        }
        return true;
    }
}
