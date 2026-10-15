package Main;

// Import loaders packages
import loaders.*;

// Import task2 packages
import task2.*;

import java.util.List;

public class Task2 {
    public static void main(String[] args) {
        // Load demo data by initializing a list of cruises
        List<Cruise> cruises = DataLoader.initializeCruises();

        // Create a controller instance with the loaded cruise data
        CruiseControl controller = new CruiseControl(cruises);

        // Instantiate the main menu, passing the controller to manage operations
        MainMenu menu = new MainMenu(controller);

        // Display the main menu to the user
        menu.displayMenu();
    }
}
