package se.kjellstrand.tlmfruits.entries.model;

public class Fruit {
    public int fruitId;
    public String fruitType;
    public int amount;

    @Override
    public String toString() {
        return "Fruit{" +
                "fruitId=" + fruitId +
                ", fruitType='" + fruitType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
