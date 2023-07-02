public class Rook extends Piece{
    boolean white;
    boolean alive = true;

    Rook(boolean white){
        this.white = white;
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
