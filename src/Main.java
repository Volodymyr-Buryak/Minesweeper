import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner reader = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int number = reader.nextInt();

            System.out.println("You entered: " + number);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
