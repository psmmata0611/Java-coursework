package task1_coursework2;

import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice ;
        do {
            try {
                // Display the menu
                SuperBowls.mainMenu();

                // Get user input
                System.out.print("Enter your choice:> ");
                choice = scanner.nextInt();

                // Execute the chosen option
                switch (choice) {
                    case 1:
                        SuperBowls.listInfo(scanner);
                        break; // Prevent fall-through
                    case 2:
                        SuperBowls.select(scanner);
                        break; 
                    case 3:
                        SuperBowls.searchMenu(scanner);
                        break; 
                    case 0:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0 and 3.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the scanner buffer to handle invalid input
                choice = -1;
            }
        } while (choice != 0);
    }
}
