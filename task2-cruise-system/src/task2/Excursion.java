
package task2;

import java.util.ArrayList;
import java.util.List;

public class Excursion {
    private String destination;
    private String dayOfWeek;
    private int capacity;
    private List<Passenger> passengers;

    public Excursion(String destination, String dayOfWeek, int capacity) {
        this.destination = destination;
        this.dayOfWeek = dayOfWeek;
        this.capacity = capacity;
        this.passengers = new ArrayList<>();
    }

    public String getDestination() {
        return destination;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getAvailableSpots() {
        return capacity - passengers.size();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        if (getAvailableSpots() > 0) {
            passengers.add(passenger);
        }
    }
}
