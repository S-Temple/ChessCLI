public class Queen extends Piece{
    boolean white = true;
    boolean alive = true;

    Queen(boolean white){
        this.white = white;
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
