/**
 * Represents a Pawn piece in a chess game, which is a subclass of {@code Piece}.
 * <p>
 * A pawn has special movement rules in chess, including the ability to move two
 * spaces forward on its first move. To track this, the {@code firstMove} flag
 * is added. This flag is used to determine whether the pawn is eligible to make
 * a two-space move.
 * <p>
 * The {@code Board} class manages the state of the game, including determining
 * whether a move is ultimately valid. Even if a move passes this local
 * {@code validMove} method, the {@code firstMove} flag is not toggled locally.
 */
public class Pawn extends Piece{

    /**
     * Tracks whether the pawn has made its first move.
     * <p>
     * If {@code true}, the pawn can move two spaces forward; otherwise, it
     * can only move one space forward.
     */
    boolean firstMove;

    /**
     * Constructs a {@code Pawn} object with a specified color.
     *
     * @param white {@code true} if the pawn is white, {@code false} if black
     */
    Pawn(boolean white){
        super(true, white);
        firstMove = true;
    }

    /**
     * Determines if the pawn's requested movement is valid according to chess rules.
     * <p>
     * Pawns can move forward one space, or two spaces if it's their first move.
     * Pawns can also attack diagonally forward by one space.
     *
     * @param col     the starting column (a-h)
     * @param row     the starting row (1-8)
     * @param colDest the requested destination column (a-h)
     * @param rowDest the requested destination row (1-8)
     * @return {@code true} if the move is valid, {@code false} otherwise
     */
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {

        int vert = rowDest - row;
        int horiz = colDest - col;
        // Rule out invalid moves with excessive vertical or horizontal movement
        if (Math.abs(vert) > 2 || Math.abs(horiz) > 1) return false;

        if(this.firstMove){
            // Special first-move behavior: two-space move allowed
            if(this.white){
                // moves up
                if (Math.abs(horiz) == 1 && 1 == vert) return true; // attack move off the line
                else if (horiz == 0 && (vert == 1 || vert == 2)) return true; // forward advance
            } else {
                //moves down
                if (Math.abs(horiz) == 1 && -1 == vert) return true; // attack move off the line
                else if (horiz == 0 && (vert == -1 || vert == -2)) return true; // forward advance
            }
        } else {
            // Standard pawn movement (one space forward or diagonal attack)
            if(this.white){
                // moves up
                if (Math.abs(horiz) == 1 && 1 == vert) return true; // attack move off the line
                else if (horiz == 0 && vert == 1) return true; // forward advance
            } else {
                //moves down
                if (Math.abs(horiz) == 1 && -1 == vert) return true; // attack move off the line
                else if (horiz == 0 && vert == -1 ) return true; // forward advance
            }
        }
        return false;
    }

    /**
     * Marks the pawn as having made its first move by setting the {@code firstMove} flag to {@code false}.
     * <p>
     * This method is called when the pawn completes its first move, preventing it from moving two spaces
     * in subsequent turns.
     */
    public void removeFirstMove(){
        this.firstMove = false;
    }


    /**
     * Returns a string representation of the pawn.
     * <p>
     * The format is "{@code WP}" for a white pawn and "{@code BP}" for a black pawn,
     * where "W" and "B" represent the color of the piece.
     *
     * @return a string representing the pawn's color and type (e.g., "WP" or "BP")
     */
    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "P";
    }
}
