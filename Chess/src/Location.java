public class Location {
    private int row;
    private char column;
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
