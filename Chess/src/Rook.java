public class Rook extends Piece{

    Rook(boolean white){
        super(true, white);
    }
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if((colDest == col && rowDest != row) | (colDest != col && rowDest == row)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "R";
    }
}
