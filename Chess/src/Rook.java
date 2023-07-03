public class Rook extends Piece{

    Rook(boolean white){
        super(true, white);
    }
    void validMoves() {}

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + " R";
    }
}
