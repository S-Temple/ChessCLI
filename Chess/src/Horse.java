public class Horse extends Piece{
    boolean white = true;
    boolean alive = true;

    Horse(boolean white){
        this.white = white;
    }
    void validMoves() {}

    @Override
    public String toString() {
        String colour;
        if (this.white) colour = "W";
        else colour = "B";
        return colour + " H";
    }
}
