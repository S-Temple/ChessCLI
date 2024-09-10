import java.util.Scanner;

/**
 * Abstracts the System.in calls and input validation from the rest of the program
 */
public class ChessCLI {
    Scanner reader = new Scanner(System.in);

    /**
     * Returns an int representing the user selected row on a chess board
     * contains all the input validation and will continue until valid entry is made
     *
     * @param  prompt A string that provides context to user on what to input
     * @return      the int representing the users selected row
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
     * Shortens System.out.print()
     *
     * @param  string A string that will be printed via system.out.print call
     */
    public void print(String string){
        System.out.print(string);
    }

    /**
     * May build out later for adding game menu
     */
    public String getUserString() {
        return "get";
    }

    /**
     * Returns an char representing the user selected column on a chess board
     * contains all the input validation and will continue until valid entry is made
     *
     * @param  prompt A string that provides context to user on what to input
     * @return      the char representing the users selected row
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
