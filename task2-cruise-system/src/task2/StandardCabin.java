package task2;


public class StandardCabin extends Cabin {
    private boolean SeaView;

    public StandardCabin(int number, boolean hasSeaView) {
        super(number);
        this.SeaView = hasSeaView;
    }

    public boolean hasSeaView() {
        return SeaView;
    }

    @Override
    public int getCapacity() {
        return 6;
    }
}
