
package task2;

public abstract class Cabin {
    private final int number;

    public Cabin(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public abstract int getCapacity(); 
}


