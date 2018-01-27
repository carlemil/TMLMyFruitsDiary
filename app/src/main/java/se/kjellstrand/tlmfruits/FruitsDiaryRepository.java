package se.kjellstrand.tlmfruits;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.kjellstrand.tlmfruits.entries.model.Entry;
import se.kjellstrand.tlmfruits.entries.model.Fruit;
import se.kjellstrand.tlmfruits.entries.model.PostEntry;

@Singleton
public class FruitsDiaryRepository {

    private FruitsDiaryService service;

    public FruitsDiaryRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fruitdiary.test.themobilelife.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FruitsDiaryService.class);
    }

    public LiveData<List<Entry>> getEntries() {
        // Add a in memory cache to avoid hitting the network for configuration changes
        final MutableLiveData<List<Entry>> data = new MutableLiveData<>();
        service.getEntries().enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                Log.e("TAG", "Got Response.");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                // TODO handle error case
                Log.e("TAG", "Error: " + t);
            }
        });
        return data;
    }

    public LiveData<List<Fruit>> getFruits() {
        // Add a in memory cache to avoid hitting the network for configuration changes
        final MutableLiveData<List<Fruit>> data = new MutableLiveData<>();
        service.getFruits().enqueue(new Callback<List<Fruit>>() {
            @Override
            public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {
                Log.e("TAG", "Got Response.");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Fruit>> call, Throwable t) {
                // TODO handle error case
                Log.e("TAG", "Error: " + t);
            }
        });
        return data;
    }

    public LiveData<Entry> addEntry(PostEntry entry) {
        // Add a in memory cache to avoid hitting the network for configuration changes
        final MutableLiveData<Entry> data = new MutableLiveData<Entry>();
        service.addEntry(entry).enqueue(new Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                Log.e("TAG", "Got Response.");
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {
                // TODO handle error case
                Log.e("TAG", "Error: " + t);
            }
        });
        return data;
    }
}
