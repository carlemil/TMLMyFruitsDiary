package se.kjellstrand.tlmfruits.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entry {

    public int id;

    public String date;

    @SerializedName("fruit")
    public List<EntryFruit> entryFruit;

    public Entry(int id, String date, List<EntryFruit> entryFruit) {
        this.id = id;
        this.date = date;
        this.entryFruit = entryFruit;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", entryFruit=" + entryFruit +
                '}';
    }
}
