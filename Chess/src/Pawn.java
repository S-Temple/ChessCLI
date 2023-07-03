public class Pawn extends Piece{

    Pawn(boolean white){
        super(true, white);
    }
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
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
        return colour + " P";
    }
}
