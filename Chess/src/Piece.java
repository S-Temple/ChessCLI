class Piece {

    boolean white;
    boolean alive;

    Piece() {
        this.alive = false;
    }

    Piece(boolean alive, boolean white){
        this.alive = alive;
        this.white = white;

    }
    void validMoves() {}

    @Override
    public String toString() {
        return "   ";
    }
}
