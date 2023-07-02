public class Pawn extends Piece{
    boolean white;
    boolean alive = true;

    Pawn(boolean white){
        this.white = white;
    }
    void validMoves() {}

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + " P";
    }
}
