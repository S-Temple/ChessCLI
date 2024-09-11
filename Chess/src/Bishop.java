/**
 * Represents a Bishop piece in a chess game, which is a subclass of {@code Piece}.
 * <p>
 * The bishop can move diagonally any number of squares but cannot move horizontally or vertically.
 * This class overrides the {@code validMove()} method to enforce the movement rules of the bishop
 * and provides a string representation of the bishop.
 */
public class Bishop extends Piece{

    /**
     * Constructs a new {@code Bishop} object with a specified color.
     *
     * @param white {@code true} if the bishop is white, {@code false} if black
     */
    Bishop(boolean white){
        super(true, white);
    }

    /**
     * Determines if the bishop's requested movement is valid according to chess rules.
     * <p>
     * A bishop can only move diagonally, meaning the absolute difference between the
     * starting and destination columns must be equal to the absolute difference
     * between the starting and destination rows.
     *
     * @param col     the starting column (a-h)
     * @param row     the starting row (1-8)
     * @param colDest the requested destination column (a-h)
     * @param rowDest the requested destination row (1-8)
     * @return {@code true} if the move is valid for a bishop, {@code false} otherwise
     */
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if (Math.abs(colDest - col) == Math.abs(rowDest - row)) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the bishop.
     * <p>
     * The format is "{@code WB}" for a white bishop and "{@code BB}" for a black bishop,
     * where "W" and "B" represent the color of the piece.
     *
     * @return a string representing the bishop's color and type (e.g., "WB" or "BB")
     */
    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "B";
    }
}
