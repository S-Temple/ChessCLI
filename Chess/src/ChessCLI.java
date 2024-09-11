import java.util.Scanner;

/**
 * Provides a command-line interface (CLI) for input handling and validation in the chess game.
 * <p>
 * This class abstracts user input using the {@code System.in} and provides methods to retrieve
 * validated input from the user, including row (integer) and column (character) selections for chess moves.
 */
public class ChessCLI {
    Scanner reader = new Scanner(System.in);

    /**
     * Prompts the user for a row number and validates the input.
     * <p>
     * The method repeatedly asks the user for a valid row number (between 1 and 8) until
     * a correct input is provided. If the input is not an integer or is out of range,
     * an error message is shown, and the input is requested again.
     *
     * @param prompt A string that provides context for the user on what input is expected
     * @return The integer representing the user's selected row (1 to 8)
     */
    public int getUserInt(String prompt){
        boolean validInput = false;
        int input = 0;

        System.out.println(prompt);

        while (!validInput) {
            if (reader.hasNextInt()) {
                input = reader.nextInt();
                if (input > 0 && input < 9) validInput = true;
                else {
                    System.out.println("Please enter a valid row");
                }
            } else {
                System.out.println("Please enter a valid row");
                reader.next();
            }
        }
        return input;
    }

    /**
     * Prints the provided string to the console.
     * <p>
     * This method is a shortcut to {@code System.out.print()} for convenience.
     *
     * @param string The string to be printed via {@code System.out.print()}
     */
    public void print(String string){
        System.out.print(string);
    }

    /**
     * Placeholder for future string input handling ( game menu and quiting).
     * <p>
     * This method currently returns a default string but can be extended later.
     *
     * @return A string value (currently a placeholder)
     */
    public String getUserString() {
        return "get";
    }

    /**
     * Prompts the user for a column character and validates the input.
     * <p>
     * The method repeatedly asks the user for a valid column (from 'a' to 'h') until
     * a correct input is provided. If the input is invalid, an error message is shown,
     * and the input is requested again.
     *
     * @param prompt A string that provides context for the user on what input is expected
     * @return The character representing the user's selected column ('a' to 'h')
     */
    public char getUserChar(String prompt) {
        boolean validInput = false;
        String validChars = "abcdefgh";
        char input = 'i';
        String sInput;

        System.out.println(prompt);

        while (!validInput) {
            if (reader.hasNext()) {
                sInput = reader.next().strip().toLowerCase();
                if (sInput.length() == 1 && validChars.contains(sInput)) {
                    input = sInput.charAt(0);
                    validInput = true;
                }
                else {
                    System.out.println("Please enter a valid column");
                }
            } else {
                System.out.println("Please enter a valid column");
                reader.next();
            }
        }
        return input;
    }


}
