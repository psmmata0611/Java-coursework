package loaders;

import task2.*;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<Cruise> initializeCruises() {
        List<Cruise> cruises = new ArrayList<>();

        // Create Ship 1
        Ship ship1 = new Ship("Mediterranean Queen B.");
        ship1.addCabin(new Suite(101, true)); // Suite with a balcony
        ship1.addCabin(new Suite(102, false)); // Suite without a balcony
        ship1.addCabin(new StandardCabin(201, true)); // Standard cabin with a sea view
        ship1.addCabin(new StandardCabin(202, false)); // Standard cabin without a sea view

        // Create Cruise 1
        Cruise cruise1 = new Cruise("Mediterranean Summer", ship1);
        cruise1.addExcursion(new Excursion("Athens", "Monday", 5));
        cruise1.addExcursion(new Excursion("Santorini", "Wednesday", 20));
        cruise1.addExcursion(new Excursion("Mykonos", "Friday", 15));

        // Add Passengers to Cruise 1
        Passenger passenger1 = new Passenger("Johnathan Davinson");
        passenger1.setCabin(ship1.getCabins().get(0)); // Assign to Suite 101
        cruise1.addPassenger(passenger1);

        Passenger passenger2 = new Passenger("Samanta Rose");
        passenger2.setCabin(ship1.getCabins().get(1)); // Assign to Suite 102
        cruise1.addPassenger(passenger2);

        cruises.add(cruise1);

        // Create Ship 2
        Ship ship2 = new Ship("Aegean Star");
        ship2.addCabin(new Suite(301, true)); // Suite with a balcony
        ship2.addCabin(new StandardCabin(302, true)); // Standard cabin with a sea view

        // Create Cruise 2
        Cruise cruise2 = new Cruise("Aegean Wonders", ship2);
        cruise2.addExcursion(new Excursion("Rhodes", "Tuesday", 7));
        cruise2.addExcursion(new Excursion("Crete", "Thursday", 25));
        cruise2.addExcursion(new Excursion("Delos", "Sunday", 12));

        // Add Passengers to Cruise 2
        Passenger passenger3 = new Passenger("Alissia James");
        passenger3.setCabin(ship2.getCabins().get(0)); // Assign to Suite 301
        cruise2.addPassenger(passenger3);

        Passenger passenger4 = new Passenger("James J. Johnson");
        passenger4.setCabin(ship2.getCabins().get(1)); // Assign to Standard Cabin 302
        cruise2.addPassenger(passenger4);

        cruises.add(cruise2);

        return cruises;
    }
}
