package se.kjellstrand.tlmfruits.model;

public class EntryFruit {
    public int fruitId;
    public int amount;

    @Override
    public String toString() {
        return "EntryFruit{" +
                "fruitId=" + fruitId +
                ", amount=" + amount +
                '}';
    }
}
