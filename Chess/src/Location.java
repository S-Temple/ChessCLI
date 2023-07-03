public class Location {
    public int row;
    public char column;
    Piece piece;

    Location(char column, int row) {
        this.row = row;
        this.column = column;
        this.piece = new Piece();
    }

    boolean validMove(char colDest, int rowDest){
        return this.piece.validMove(this.column , this.row, colDest, rowDest);
    }

    @Override
    public String toString() {
        return this.column + "," + this.row;
    }
}
