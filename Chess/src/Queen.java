/**
 * Represents a Queen piece in a chess game, which is a subclass of {@code Piece}.
 * <p>
 * The queen is the most powerful piece in chess and can move any number of squares
 * along a row, column, or diagonal. This class overrides the {@code validMove()}
 * method to implement queen-specific movement, combining the movement rules of the
 * rook and bishop.
 */
public class Queen extends Piece{

    /**
     * Constructs a new {@code Queen} object with a specified color.
     *
     * @param white {@code true} if the queen is white, {@code false} if black
     */
    Queen(boolean white){
        super(true, white);
    }

    /**
     * Determines if the queen's requested movement is valid according to chess rules.
     * <p>
     * The queen can move any number of squares in a straight line along a row, column,
     * or diagonal. This method checks whether the move is valid for a queen based
     * on these movement patterns.
     *
     * @param col     the starting column (a-h)
     * @param row     the starting row (1-8)
     * @param colDest the requested destination column (a-h)
     * @param rowDest the requested destination row (1-8)
     * @return {@code true} if the move is valid for a queen, {@code false} otherwise
     */
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if (Math.abs(colDest - col) == Math.abs(rowDest - row)) { // diagonal movement
            return true;
        }
        else if ((colDest == col && rowDest != row) || (colDest != col && rowDest == row)){ // orthogonal movement
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the queen.
     * <p>
     * The format is "{@code WQ}" for a white queen and "{@code BQ}" for a black queen,
     * where "W" and "B" represent the color of the piece.
     *
     * @return a string representing the queen's color and type (e.g., "WQ" or "BQ")
     */
    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "Q";
    }
}
