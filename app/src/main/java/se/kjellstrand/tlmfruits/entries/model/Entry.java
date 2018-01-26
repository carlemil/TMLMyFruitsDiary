package se.kjellstrand.tlmfruits.entries.model;

import java.util.List;

public class Entry {
    public int id;
    public String date;
    public List<Fruit> fruit;

    public Entry(int id, String date, List<Fruit> fruit) {
        this.id = id;
        this.date = date;
        this.fruit = fruit;
    }
}
