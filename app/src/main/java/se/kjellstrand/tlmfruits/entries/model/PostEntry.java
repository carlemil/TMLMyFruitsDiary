package se.kjellstrand.tlmfruits.entries.model;

import android.util.Log;

public class PostEntry {
    public String date;

    public PostEntry(String date) {
        Log.d("TAG", "New entry, date :" + date);
        this.date = date;
    }
}
