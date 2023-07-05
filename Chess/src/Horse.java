public class Horse extends Piece{

    Horse(boolean white){
        super(true, white);
    }
    @Override
    boolean validMove(char col, int row, char colDest, int rowDest) {
        if(Math.abs(colDest - col) + Math.abs(rowDest - row) == 3 && (Math.abs(colDest - col) > 0) && (Math.abs(rowDest - row) > 0)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + "H";
    }
}
