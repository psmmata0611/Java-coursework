package task1_coursework2;

import java.util.*;

public class SuperBowls {

    private int year;
    private String date;
    private String superbowlNumber;
    private String winningTeam;
    private int winningPoints;
    private String losingTeam;
    private int losingPoints;
    private String mvp;
    private String stadium;
    private String city;
    private String state;

    SuperBowls() {
    }

    public SuperBowls(int year, String date, String superbowlNumber, String winningTeam, int winningPoints,
            String losingTeam, int losingPoints, String mvp, String stadium, String city, String state) {

        this.year = year;
        this.date = date;
        this.superbowlNumber = superbowlNumber;
        this.winningTeam = winningTeam;
        this.winningPoints = winningPoints;
        this.losingTeam = losingTeam;
        this.losingPoints = losingPoints;
        this.mvp = mvp;
        this.stadium = stadium;
        this.city = city;
        this.state = state;
    }

    //Getter methods
    public int getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getsuperbowlNumber() {
        return superbowlNumber;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public int getWinningPoints() {
        return winningPoints;
    }

    public String getLosingTeam() {
        return losingTeam;
    }

    public int getLosingPoints() {
        return losingPoints;
    }

    public String getMvp() {
        return mvp;
    }

    public String getStadium() {
        return stadium;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    // Method to display the Main Menu
    public static void mainMenu() {
        System.out.println("-----------------------");
        System.out.println("NFL Superbowls menu");
        System.out.println("-----------------------");
        System.out.println("List .................1");
        System.out.println("Select ...............2");
        System.out.println("Search................3");
        System.out.println("Exit..................0");
        System.out.println("-----------------------");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------------------------------------------------------------\n");
        sb.append("|                                                                                         |\n");
        sb.append(String.format("| %-87s |\n", "Superbowl " + superbowlNumber));
        sb.append(String.format("| %-87s |\n", "Venue: " + stadium + " in " + city + ", " + state));
        sb.append(String.format("| %-87s |\n",
                "The " + winningTeam + " beat the " + losingTeam
                + " by " + winningPoints + " points to " + losingPoints + " points"));
        sb.append(String.format("| %-87s |\n", "The most valuable player award went to " + mvp));
        sb.append("|                                                                                         |\n");
        sb.append("------------------------------------------------------------------------------------------");
        return sb.toString();
    }

    //Method for listing Super Bowl details based on a start and end year range provided by the user, generating a formatted table
    public static void listInfo(Scanner scanner) {
        DataLoader DL = new DataLoader();
        ArrayList<SuperBowls> index = DL.getSuperB();

        // Prompt the user for the start and end years
        System.out.print("Enter start year > ");
        int startYear = scanner.nextInt();

        System.out.print("Enter end year > ");
        int endYear = scanner.nextInt();

        // Validate the year range
        if (startYear > endYear) {
            System.out.println("Start year must be less than or equal to end year.");
            return;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-13s | %-25s | %-25s |\n", "Year", "Superbowl No.", "Champions", "Runners-up");
        System.out.println("--------------------------------------------------------------------------------");

        // Track if any data was found
        boolean found = false;

        // Iterate over the data and filter by the specified year range
        for (SuperBowls sb : index) {
            if (sb.getYear() >= startYear && sb.getYear() <= endYear) {
                found = true;
                // Print the filtered Super Bowl details
                System.out.printf("| %-4d | %-13s | %-25s | %-25s |\n",
                        sb.getYear(), sb.getsuperbowlNumber(), sb.getWinningTeam(), sb.getLosingTeam());
            }
        }

        // If no data found, notify the user
        if (!found) {
            System.out.println("No Superbowl information found for the given range.");
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Method to select a specific Superbowl year and view full details in a toString() method
    public static void select(Scanner scanner) {
        DataLoader DL = new DataLoader();
        ArrayList<SuperBowls> index = DL.getSuperB();

        System.out.print("Enter championship year > ");
        int year = scanner.nextInt();

        boolean found = false;
        for (SuperBowls sb : index) {
            if (sb.getYear() == year) {
                System.out.println(sb.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No Superbowl found for the given year.");
        }
    }

    public static void searchMenu(Scanner scanner) {
        int searchChoice;

        // Display menu
        System.out.println("-----------------------");
        System.out.println("Search Superbowls by:");
        System.out.println("-----------------------");
        System.out.println("Team .................1");
        System.out.println("State.................2");
        System.out.println("Main menu.............0");
        System.out.println("-----------------------");
        System.out.print("Enter choice:> ");

        // Ensure valid integer input
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number (0, 1, or 2): ");
            scanner.next(); // Consume invalid input
        }
        searchChoice = scanner.nextInt();
        scanner.nextLine(); // Clear newline left in the buffer

        // Process user choice
        switch (searchChoice) {
            case 1:
                searchByTeam(scanner); // Call team search
                break;
            case 2:
                searchByState(scanner); // Call state search
                break;
            case 0:
                break; // back to the main menu
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    // Method to search Superbowls by team, displaying in a table the the team name, number of apperances and details(year,superbowl Number)
   private static void searchByTeam(Scanner scanner) {
    // Load data from DataLoader class
    DataLoader DL = new DataLoader();
    ArrayList<SuperBowls> index = DL.getSuperB(); // Load list of SuperBowls

    // Create a map to organize data for quick lookups by team
    Map<String, List<String>> teamAppearances = new HashMap<>();

    for (SuperBowls sb : index) {
        // Add winning team data
        String winningDetails = sb.getYear() + " (" + sb.getsuperbowlNumber() + "), Winner";
        teamAppearances
            .computeIfAbsent(sb.getWinningTeam(), k -> new ArrayList<>())
            .add(winningDetails);

        // Add losing team data
        String losingDetails = sb.getYear() + " (" + sb.getsuperbowlNumber() + "), Runner-up";
        teamAppearances
            .computeIfAbsent(sb.getLosingTeam(), k -> new ArrayList<>())
            .add(losingDetails);
    }

    // Prompt the user for the team name
    System.out.print("Enter search term for NFL team (e.g., Patriots) > ");
    String teamSearch = scanner.nextLine().trim().toLowerCase(); // Capture user input and ignore case

    // Header for the table
    System.out.println("");
    System.out.println("---------------------------------------------------------------------------");
    System.out.printf("| %-25s | %-16s | %-25s|\n", "Team", "No. appearances", "Details");
    System.out.println("---------------------------------------------------------------------------");

    boolean found = false; // Track whether any results are found

    // Iterate through the map to find matching teams
    for (Map.Entry<String, List<String>> entry : teamAppearances.entrySet()) {
        String teamName = entry.getKey();
        if (teamName.toLowerCase().contains(teamSearch)) {
            List<String> details = entry.getValue();
            System.out.printf("| %-25s | %-16s |\n", teamName, details.size());
            for (String detail : details) {
                System.out.printf("| %-25s | %-16s | %-25s|\n", "", "", detail);
            }
            found = true;
        }
    }

    if (!found) {
        System.out.println("No results found for team: " + teamSearch);
    }
    System.out.println("---------------------------------------------------------------------------");
    searchMenu(scanner);
}

    
    // Method to search Superbowls by State displaying in a table the State name,SuperBowl(superbowl number, year), city and stadium 
    private static void searchByState(Scanner scanner) {
        // Load data from DataLoader class
        DataLoader DL = new DataLoader();
        ArrayList<SuperBowls> index = DL.getSuperB(); // Load list of SuperBowls

        // Prompt user for the state name
        System.out.print("Enter search term for U.S. state (e.g., Florida) > ");
        String stateSearch = scanner.nextLine().trim(); // Capture user input and remove whitespace

        // Header for the output table
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-40s |\n", "State", "Superbowl", "City & Stadium");
        System.out.println("---------------------------------------------------------------------------");

        boolean found = false; // Flag to check if any results are found
        boolean firstStatePrint = true; // Flag to print the state name only once

        // Iterate through all SuperBowls in the dataset
        for (SuperBowls sb : index) {
            // Check if the state matches (case-insensitive)
            if (sb.getState().equalsIgnoreCase(stateSearch)) {
                found = true; // Set found to true if there's a match

                // Print the state name only once
                String state = firstStatePrint ? sb.getState() : "";
                firstStatePrint = false; // After the first print, set the flag to false

                // Print the result in a tabular format
                System.out.printf("| %-10s | %-15s | %-40s |\n",
                        state, sb.getsuperbowlNumber() + " (" + sb.getYear() + ")", sb.getCity() + ", " + sb.getStadium());
            }
        }

        // If no results are found, notify the user
        if (!found) {
            System.out.println("No results found for state: " + stateSearch);
        }

        // End of table
        System.out.println("---------------------------------------------------------------------------");

        // Return to search menu
        searchMenu(scanner);
    }

}
