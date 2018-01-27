package se.kjellstrand.tlmfruits.repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import se.kjellstrand.tlmfruits.model.Entry;
import se.kjellstrand.tlmfruits.model.PostEntry;
import se.kjellstrand.tlmfruits.model.Fruit;

public interface FruitsDiaryService {
    @GET("api/fruit")
    Call<List<Fruit>> getFruits();

    @GET("api/entries")
    Call<List<Entry>> getEntries();

    @POST("api/entries")
    Call<Entry> addEntry(@Body PostEntry entry);

    @DELETE("api/entry/{entryId}")
    Call<Entry> deleteEntry(@Path("entryId") int id);
}
