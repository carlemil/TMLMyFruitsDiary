package se.kjellstrand.tlmfruits;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import se.kjellstrand.tlmfruits.entries.model.Entry;
import se.kjellstrand.tlmfruits.entries.model.Fruit;

public interface FruitsDiaryService {
    @GET("api/fruit")
    Call<List<Fruit>> getFruits();

    @GET("api/entries")
    Call<List<Entry>> getEntries();
}
