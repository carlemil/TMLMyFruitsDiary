package se.kjellstrand.tlmfruits.entries;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import se.kjellstrand.tlmfruits.FruitsDiaryRepository;
import se.kjellstrand.tlmfruits.entries.model.Entry;

public class EntriesViewModel extends ViewModel {

    // TODO @Inject with Dagger 2
    public FruitsDiaryRepository fruitsDiaryRepository;

    public EntriesViewModel() {
        fruitsDiaryRepository = new FruitsDiaryRepository();
    }

    public LiveData<List<Entry>> getEntries() {

        return fruitsDiaryRepository.getEntries();
    }
}