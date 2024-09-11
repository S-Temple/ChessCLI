/**
 * Represents a King piece in a chess game, which is a subclass of {@code Piece}.
 * <p>
 * The king can move exactly one square in any direction: horizontally, vertically,
 * or diagonally. This class overrides the {@code validMove()} method to enforce
 * these movement rules for the king.
 */
public class King extends Piece {

    /**
     * Constructs a new {@code King} object with a specified color.
     *
     * @param white {@code true} if the king is white, {@code false} if black
     */
    King(boolean white){
        super(true, white);
    }

    /**
     * Determines if the king's requested movement is valid according to chess rules.
     * <p>
     * The king can move exactly one square in any direction: horizontally, vertically,
     * or diagonally. This method checks whether the move is within one square distance
     * from the starting position in any direction.
     *
     * @param col     the starting column (a-h)
     * @param row     the starting row (1-8)
     * @param colDest the requested destination column (a-h)
     * @param rowDest the requested destination row (1-8)
     * @return {@code true} if the move is valid for a king, {@code false} otherwise
     */
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if((Math.abs(rowDest - row) < 2) && (Math.abs(colDest - col) < 2)){
            return true;
        }
        return false;
    }


    /**
     * Returns a string representation of the king.
     * <p>
     * The format is "{@code WK}" for a white king and "{@code BK}" for a black king,
     * where "W" and "B" represent the color of the piece.
     *
     * @return a string representing the king's color and type (e.g., "WK" or "BK")
     */
    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "K";
    }


}
