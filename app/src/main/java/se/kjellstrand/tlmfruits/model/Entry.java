package se.kjellstrand.tlmfruits.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entry {

    public String id;

    public String date;

    public int nbrOfFruits;

    public int nbrOfVitamins;

    @SerializedName("fruit")
    public List<EntryFruit> entryFruit;

    public Entry(String id, String date, List<EntryFruit> entryFruit) {
        this.id = id;
        this.date = date;
        this.entryFruit = entryFruit;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", nbrOfFruits=" + nbrOfFruits +
                ", nbrOfVitamins=" + nbrOfVitamins +
                '}';
    }
}
