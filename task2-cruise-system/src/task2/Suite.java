package task2;


public class Suite extends Cabin {
    private boolean Balcony;

    public Suite(int number, boolean hasBalcony) {
        super(number);
        this.Balcony = hasBalcony;
    }

    public boolean hasBalcony() {
        return Balcony;
    }

    @Override
    public int getCapacity() {
        return 4;
    }
}

