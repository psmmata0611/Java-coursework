package Main;

import loaders.CruiseControl;
import task2.*;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final CruiseControl cruiseControl;
    private final Scanner scanner;

    public MainMenu(CruiseControl cruiseControl) {
        this.cruiseControl = cruiseControl;
        this.scanner = new Scanner(System.in);
    }

// Displays the main menu and handles user interactions in a loop
    public void displayMenu() {
        while (true) {
            try {
                System.out.println("\nCruise Ship Management System");
                System.out.println("1. Display Cruise Information");
                System.out.println("2. Display Excursion Information");
                System.out.println("3. Display Passenger Information");
                System.out.println("4. Book Excursion");
                System.out.println("5. Change Passenger Cabin");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        CruiseInformation();
                        break;
                    case 2:
                        ExcursionInformation();
                        break;
                    case 3:
                        PassengerInformation();
                        break;
                    case 4:
                        bookingExcursion();
                        break;
                    case 5:
                        changePassengerCabin();
                        break;
                    case 6: {
                        System.out.println("Exiting the system.");
                        return;
                    }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a number corresponding to the menu options.");
                scanner.nextLine();
            }
        }

    }

    /**
     * Handles the "Display Cruise Information" menu option. Retrieves and
     * displays details about available cruises and their associated
     * information.
     */
    private void CruiseInformation() {
        System.out.println("\n--- Cruise Information ---");
        // Retrieve the list of all cruises
        List<Cruise> cruises = cruiseControl.getAllCruises();

        // Check if cruises are available
        if (cruises.isEmpty()) {
            System.out.println("No cruises available.");
            return;
        }
        // Display cruise names for selection
        for (int i = 0; i < cruises.size(); i++) {
            System.out.println((i + 1) + ". " + cruises.get(i).getName());
        }

        System.out.print("Select a cruise: ");
        try {
            int cruiseIndex = scanner.nextInt() - 1; // Adjust to zero-based index
            scanner.nextLine();

            Cruise selectedCruise = cruiseControl.getCruiseByIndex(cruiseIndex);
            if (selectedCruise == null) {
                System.out.println("Invalid selection. Returning to menu.");
                return;
            }

            // Display cruise and ship information
            System.out.println("Cruise Name: " + selectedCruise.getName());
            System.out.println("Ship Name: " + selectedCruise.getShip().getName());
            System.out.println("Passengers: " + selectedCruise.getPassengers().size());
            System.out.println("Excursions:");
            for (Excursion excursion : selectedCruise.getExcursions()) {
                System.out.println("- Destination: " + excursion.getDestination()
                        + ", Day: " + excursion.getDayOfWeek()
                        + ", Available Spots: " + excursion.getAvailableSpots());
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Returning to menu.");
            scanner.nextLine(); // Consume invalid input to prevent infinite loop
        }
    }

    /**
     * Handles the "Display Excursion Information" menu option. Shows details
     * about excursions for a selected cruise.
     */
    private void ExcursionInformation() {
        System.out.println("\n--- Excursion Information ---");
        List<Cruise> cruises = cruiseControl.getAllCruises();

        for (int i = 0; i < cruises.size(); i++) {
            System.out.println((i + 1) + ". " + cruises.get(i).getName());
        }

        System.out.print("Select a cruise: ");
        int cruiseIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        // Fetch the selected cruise and its excursions
        Cruise selectedCruise = cruiseControl.getCruiseByIndex(cruiseIndex);
        if (selectedCruise == null) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }
        // Display excursion details
        List<Excursion> excursions = cruiseControl.getExcursionsForCruise(selectedCruise);
        if (excursions.isEmpty()) {
            System.out.println("No excursions available for this cruise.");
            return;
        }

        System.out.println("--- Excursions for Cruise: " + selectedCruise.getName() + " ---");
        for (Excursion excursion : excursions) {
            System.out.println("Destination: " + excursion.getDestination() + ", Spots Available: " + excursion.getAvailableSpots());
        }
    }

    /**
     * Handles the "Display Passenger Information" menu option. Displays
     * passenger details for a selected cruise.
     */
    private void PassengerInformation() {
        System.out.println("\n--- Passenger Information ---");
        List<Cruise> cruises = cruiseControl.getAllCruises();

        for (int i = 0; i < cruises.size(); i++) {
            System.out.println((i + 1) + ". " + cruises.get(i).getName());
        }

        System.out.print("Select a cruise: ");
        int cruiseIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Cruise selectedCruise = cruiseControl.getCruiseByIndex(cruiseIndex);
        if (selectedCruise == null) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }

        List<Passenger> passengers = cruiseControl.getPassengersForCruise(selectedCruise);
        System.out.println("--- Passengers for Cruise: " + selectedCruise.getName() + " ---");
        for (Passenger passenger : passengers) {
            System.out.println("Passenger Name: " + passenger.getName() + ", Cabin: " + passenger.getCabin().getNumber());
        }
    }

    /**
     * Handles the "Book Excursion" menu option. Allows a passenger to book an
     * excursion on a selected cruise.
     */
    private void bookingExcursion() {
        System.out.println("\n--- Book Excursion ---");

        // Step 1: Select Cruise
        List<Cruise> cruises = cruiseControl.getAllCruises();
        for (int i = 0; i < cruises.size(); i++) {
            System.out.println((i + 1) + ". " + cruises.get(i).getName());
        }

        System.out.print("Select a cruise: ");
        int cruiseIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Cruise selectedCruise = cruiseControl.getCruiseByIndex(cruiseIndex);
        if (selectedCruise == null) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }

        // Step 2: Select Passenger
        List<Passenger> passengers = cruiseControl.getPassengersForCruise(selectedCruise);
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println((i + 1) + ". " + passengers.get(i).getName());
        }

        System.out.print("Select a passenger: ");
        int passengerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Passenger selectedPassenger = cruiseControl.getPassengerByIndex(selectedCruise, passengerIndex);
        if (selectedPassenger == null) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }

        // Step 3: Select Excursion
        List<Excursion> availableExcursions = cruiseControl.getAvailableExcursionsForPassenger(selectedPassenger, selectedCruise);
        if (availableExcursions.isEmpty()) {
            System.out.println("No available excursions for this passenger.");
            return;
        }

        for (int i = 0; i < availableExcursions.size(); i++) {
            System.out.println((i + 1) + ". " + availableExcursions.get(i).getDestination());
        }

        System.out.print("Select an excursion: ");
        int excursionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (excursionIndex < 0 || excursionIndex >= availableExcursions.size()) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }
        Excursion selectedExcursion = availableExcursions.get(excursionIndex);
        System.out.println(cruiseControl.bookExcursion(selectedPassenger, selectedExcursion));
    }

    /**
     * Handles the "Change Passenger Cabin" menu option. Allows the user to
     * change a passenger's cabin on a selected cruise.
     */
    private void changePassengerCabin() {
        System.out.println("\n--- Change Passenger Cabin ---");

        //Select Cruise
        List<Cruise> cruises = cruiseControl.getAllCruises();
        for (int i = 0; i < cruises.size(); i++) {
            System.out.println((i + 1) + ". " + cruises.get(i).getName());
        }

        System.out.print("Select a cruise: ");
        int cruiseIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Cruise selectedCruise = cruiseControl.getCruiseByIndex(cruiseIndex);
        if (selectedCruise == null) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }

        //List Vacant Cabins
        List<Cabin> vacantCabins = cruiseControl.getVacantCabins(selectedCruise);
        if (vacantCabins.isEmpty()) {
            System.out.println("No vacant cabins available.");
            return;
        }

        for (int i = 0; i < vacantCabins.size(); i++) {
            System.out.println((i + 1) + ". Cabin " + vacantCabins.get(i).getNumber());
        }

        System.out.print("Select a vacant cabin: ");
        int cabinIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (cabinIndex < 0 || cabinIndex >= vacantCabins.size()) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }
        Cabin selectedCabin = vacantCabins.get(cabinIndex);

        //Select Passenger
        List<Passenger> passengers = cruiseControl.getPassengersForCruise(selectedCruise);
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println((i + 1) + ". " + passengers.get(i).getName());
        }

        System.out.print("Select a passenger: ");
        int passengerIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        Passenger selectedPassenger = cruiseControl.getPassengerByIndex(selectedCruise, passengerIndex);
        if (selectedPassenger == null) {
            System.out.println("Invalid selection. Returning to menu.");
            return;
        }

        // Change Cabin
        System.out.println(cruiseControl.changePassengerCabin(selectedPassenger, selectedCabin, selectedCruise));
    }
}
