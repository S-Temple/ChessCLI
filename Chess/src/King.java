public class King extends Piece {

    King(boolean white){
        super(true, white);
    }
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if((Math.abs(rowDest - row) < 2) && (Math.abs(colDest - col) < 2)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "K";
    }


}
