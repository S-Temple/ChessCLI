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
    boolean validMove(char col, int row, char colDest, int rowDest) {
        return false;
    }

    @Override
    public String toString() {
        return "  ";
    }
}
