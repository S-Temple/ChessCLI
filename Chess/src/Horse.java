/**
 * Represents a Knight (Horse) piece in a chess game, which is a subclass of {@code Piece}.
 * <p>
 * Named horse so two pieces don't start with 'K'
 * The knight moves in an "L" shape: two squares in one direction and one square
 * perpendicular, or one square in one direction and two squares perpendicular.
 * This class overrides the {@code validMove()} method to implement knight-specific movement.
 */
public class Horse extends Piece{

    /**
     * Constructs a new {@code Horse} object with a specified color.
     *
     * @param white {@code true} if the horse is white, {@code false} if black
     */
    Horse(boolean white){
        super(true, white);
    }

    /**
     * Determines if the knight's requested movement is valid according to chess rules.
     * <p>
     * The knight moves in an "L" shape, meaning it moves two squares in one direction
     * and one square in the perpendicular direction, or vice versa. The total distance
     * traveled must be exactly three squares, and the move must change both the row and the column.
     *
     * @param col     the starting column (a-h)
     * @param row     the starting row (1-8)
     * @param colDest the requested destination column (a-h)
     * @param rowDest the requested destination row (1-8)
     * @return {@code true} if the move is valid for a knight, {@code false} otherwise
     */
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        int horizontal = Math.abs(colDest - col);
        int vertical = Math.abs(rowDest - row);
        // Knight moves either 2 squares in one direction and 1 in the other
        return (horizontal == 2 && vertical == 1) || (horizontal == 1 && vertical == 2);
    }

    /**
     * Returns a string representation of the horse (knight).
     * <p>
     * The format is "{@code WH}" for a white horse (knight) and "{@code BH}" for a black horse (knight),
     * where "W" and "B" represent the color of the piece.
     * If 'WK' and 'BK' were used it would require another method for differentiation from King.
     *
     * @return a string representing the horse's color and type (e.g., "WH" or "BH")
     */
    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "H";
    }
}
