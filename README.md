# ChessCLI

Java source code for simple command line interface chess game

Game is OOP in design. There are 3 main components to the program. 
1. Game pieces all inherit from the Piece class that has boolean variables members 'white' & 'alive'. The base class also has two methods with signatures:
``  
    boolean validMove(char col, int row, char colDest, int rowDest)
    public String toString()
``
2. The Board is essentially a array of arrays 8 by 8 with methods for managing the Locations and resetting the board.

3. The entrypoint Main controls game flow and instantiates the ChesseCLI class that manages user inputs and validates inputs.
