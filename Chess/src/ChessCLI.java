import java.util.Scanner;

public class ChessCLI {
    Scanner reader = new Scanner(System.in);

    // all gets wait until valid input is entered
    public int getUserInt(String print){
        boolean validInput = false;
        int input = 0;

        System.out.println(print);

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

    public void print(String string){
        System.out.print(string);
    }

    public String getUserString() {
        return "get";
    }

    public char getUserChar(String print) {
        boolean validInput = false;
        String validChars = "abcdefgh";
        char input = 'a';
        String sInput;

        System.out.println(print);

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
