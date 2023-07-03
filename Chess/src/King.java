public class King extends Piece {

    King(boolean white){
        super(true, white);
    }
    void validMoves() {}

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + " K";
    }


}
