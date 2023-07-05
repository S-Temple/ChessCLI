public class Bishop extends Piece{


    Bishop(boolean white){
        super(true, white);
    }
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if (Math.abs(colDest - col) == Math.abs(rowDest - row)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "B";
    }
}
