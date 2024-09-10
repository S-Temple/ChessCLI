public class Location {
    public int row;
    public char column;
    Piece piece;

    Location(char column, int row) {
        this.row = row;
        this.column = column;
        this.piece = new Piece();
    }

    @Override
    public String toString() {
        return this.column + "," + this.row;
    }
}
