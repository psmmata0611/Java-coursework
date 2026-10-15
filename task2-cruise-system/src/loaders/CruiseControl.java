
package loaders;
import task2.*;

import java.util.List;
import java.util.stream.Collectors;

public class CruiseControl {
    private List<Cruise> cruises;

    public CruiseControl(List<Cruise> cruises) {
        this.cruises = cruises;
    }

    // Fetch all available cruises
    public List<Cruise> getAllCruises() {
        return cruises;
    }

    // Get a specific cruise by index
    public Cruise getCruiseByIndex(int index) {
        if (index >= 0 && index < cruises.size()) {
            return cruises.get(index);
        }
        return null;
    }

    // Fetch all excursions for a specific cruise
    public List<Excursion> getExcursionsForCruise(Cruise cruise) {
        return cruise.getExcursions();
    }

    // Fetch all passengers for a specific cruise
    public List<Passenger> getPassengersForCruise(Cruise cruise) {
        return cruise.getPassengers().stream()
                .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                .collect(Collectors.toList());
    }

    // Get passenger details by index for a specific cruise
    public Passenger getPassengerByIndex(Cruise cruise, int index) {
        List<Passenger> passengers = getPassengersForCruise(cruise);
        if (index >= 0 && index < passengers.size()) {
            return passengers.get(index);
        }
        return null;
    }

    // List excursions for a specific passenger (filtering based on booking status)
    public List<Excursion> getAvailableExcursionsForPassenger(Passenger passenger, Cruise cruise) {
        return cruise.getExcursions().stream()
                .filter(excursion -> !passenger.getExcursions().contains(excursion) && excursion.getAvailableSpots() > 0)
                .collect(Collectors.toList());
    }

    // Book an excursion for a passenger
    public String bookExcursion(Passenger passenger, Excursion excursion) {
        if (excursion.getAvailableSpots() > 0) {
            excursion.addPassenger(passenger);
            passenger.joinExcursion(excursion);
            return "Booking successful! " + passenger.getName() + " has joined the excursion to " + excursion.getDestination() + ".";
        }
        return "Booking failed! No spots available for this excursion.";
    }

    // Fetch vacant cabins for a cruise
    public List<Cabin> getVacantCabins(Cruise cruise) {
        List<Cabin> allCabins = cruise.getShip().getCabins();
        List<Cabin> occupiedCabins = cruise.getPassengers().stream()
                .map(Passenger::getCabin)
                .distinct()
                .collect(Collectors.toList());

        return allCabins.stream()
                .filter(cabin -> !occupiedCabins.contains(cabin))
                .collect(Collectors.toList());
    }

    // Change passenger cabin
    public String changePassengerCabin(Passenger passenger, Cabin newCabin, Cruise cruise) {
        Cabin currentCabin = passenger.getCabin();
        List<Passenger> cabinMates = getCabinMates(passenger, cruise);

        passenger.setCabin(newCabin); // move the selected passenger
        for (Passenger mate : cabinMates) {
            mate.setCabin(newCabin);      // and everyone sharing their cabin
        }
        return "Cabin changed! All cabin mates moved from Cabin " + currentCabin.getNumber() + " to Cabin " + newCabin.getNumber() + ".";
    }

    // Fetch cabin mates for a passenger
    public List<Passenger> getCabinMates(Passenger passenger, Cruise cruise) {
        return cruise.getPassengers().stream()
                .filter(p -> p.getCabin() != null && p.getCabin().equals(passenger.getCabin()) && !p.equals(passenger))
                .collect(Collectors.toList());
    }
}



    

