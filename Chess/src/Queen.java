public class Queen extends Piece{

    Queen(boolean white){
        super(true, white);
    }
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if (Math.abs(colDest - col) == Math.abs(rowDest - row)) {
            return true;
        }
        else if ((colDest == col && rowDest != row) | (colDest != col && rowDest == row)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "Q";
    }
}
