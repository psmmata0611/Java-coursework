
package task2;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private String name;
    private List<Cabin> cabins;

    public Ship(String name) {
        this.name = name;
        this.cabins = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Cabin> getCabins() {
        return cabins;
    }

    public void addCabin(Cabin cabin) {
        cabins.add(cabin);
    }
}

