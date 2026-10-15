
package task2;

import java.util.ArrayList;
import java.util.List;

public class Cruise {
    private String name;
    private Ship ship;
    private List<Excursion> excursions;
    private List<Passenger> passengers;

    public Cruise(String name, Ship ship) {
        this.name = name;
        this.ship = ship;
        this.excursions = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Ship getShip() {
        return ship;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void addExcursion(Excursion excursion) {
        excursions.add(excursion);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }
}

