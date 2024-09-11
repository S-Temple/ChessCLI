/**
 * The {@code Rook} class represents a rook piece in a chess game.
 * <p>
 * The rook can move any number of squares along a column or row,
 * but cannot move diagonally. This class extends the {@code Piece} class.
 * It overrides the {@code validMove()} method to implement rook-specific
 * movement rules and provides a string representation of the rook.
 */
public class Rook extends Piece{

    /**
     * Constructs a new {@code Rook} piece with a specified color.
     *
     * @param white {@code true} if the rook is white, {@code false} if black
     */
    Rook(boolean white){
        super(true, white);
    }

    /**
     * Checks if the move from the current position to the destination position
     * is valid according to the movement rules for a rook.
     * <p>
     * A rook can move any number of squares either horizontally or vertically.
     *
     * @param col     the starting column (a-h)
     * @param row     the starting row (1-8)
     * @param colDest the destination column (a-h)
     * @param rowDest the destination row (1-8)
     * @return {@code true} if the move is valid, {@code false} otherwise
     */
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if((colDest == col && rowDest != row) | (colDest != col && rowDest == row)){
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the rook.
     * <p>
     * The format is "{@code WR}" for a white rook and "{@code BR}" for a black rook,
     * where "W" and "B" represent the color of the piece.
     *
     * @return a string representing the rook's color and type (e.g., "WR" or "BR")
     */
    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "R";
    }
}
