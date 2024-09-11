public class Pawn extends Piece{

    boolean firstMove;

    Pawn(boolean white){
        super(true, white);
        firstMove = true;
    }

    // TODO: fix firstmove
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if (white && firstMove && (rowDest - row == 2) && (colDest == col)) {
            firstMove = false;
            return true;
        }
        if (!white && firstMove && (rowDest - row == -2) && (colDest == col)) {
            firstMove = false;
            return true;
        }

        if(white && (rowDest - row == 1) && (Math.abs(colDest - col) < 2)){
            return true;
        } else if (!white && (rowDest - row == -1) && (Math.abs(colDest - col) < 2)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "P";
    }
}
