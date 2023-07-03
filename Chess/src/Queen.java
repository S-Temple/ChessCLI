public class Queen extends Piece{

    Queen(boolean white){
        super(true, white);
    }
    void validMoves() {}

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + " Q";
    }
}
