import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner reader = new Scanner(System.in)) {
            // Read input from the user
            System.out.print("Enter a number: ");
            int number = reader.nextInt();

            // Print the number entered by the user
            System.out.println("You entered: " + number);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());

        }

    }
}
