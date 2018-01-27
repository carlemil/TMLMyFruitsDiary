package se.kjellstrand.tlmfruits;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import se.kjellstrand.tlmfruits.entries.model.Entry;
import se.kjellstrand.tlmfruits.entries.model.Fruit;
import se.kjellstrand.tlmfruits.entries.model.PostEntry;

public interface FruitsDiaryService {
    @GET("api/fruit")
    Call<List<Fruit>> getFruits();

    @GET("api/entries")
    Call<List<Entry>> getEntries();

    @POST("api/entries")
    Call<Entry> addEntry(@Body PostEntry entry);
}
