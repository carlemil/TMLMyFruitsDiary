package se.kjellstrand.tlmfruits.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

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
import se.kjellstrand.tlmfruits.entries.model.EntryFruit;
import se.kjellstrand.tlmfruits.entries.model.PostEntry;
import se.kjellstrand.tlmfruits.model.Fruit;

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

    public LiveData<Resource<List<Entry>>> getEntries() {
        // Add a in memory cache to avoid hitting the network for configuration changes
        final MutableLiveData<Resource<List<Entry>>> data = new MutableLiveData<>();
        service.getEntries().enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                if (response.code() == 200) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    public LiveData<Resource<Entry>> addEntry(PostEntry entry) {
        // Add a in memory cache to avoid hitting the network for configuration changes
        final MutableLiveData<Resource<Entry>> data = new MutableLiveData<Resource<Entry>>();
        service.addEntry(entry).enqueue(new Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                if (response.code() == 200) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    public LiveData<Resource<Entry>> deleteEntry(int id) {
        final MutableLiveData<Resource<Entry>> data = new MutableLiveData<>();
        service.deleteEntry(id).enqueue(new Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                if (response.code() == 200) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

    public LiveData<Resource<List<Fruit>>> getFruits() {
        final MutableLiveData<Resource<List<Fruit>>> data = new MutableLiveData<>();
        service.getFruits().enqueue(new Callback<List<Fruit>>() {
            @Override
            public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {
                if (response.code() == 200) {
                    data.setValue(Resource.success(response.body()));
                } else {
                    data.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<List<Fruit>> call, Throwable t) {
                data.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return data;
    }

}
