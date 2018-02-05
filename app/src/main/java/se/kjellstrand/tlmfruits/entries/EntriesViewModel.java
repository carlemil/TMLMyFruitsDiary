package se.kjellstrand.tlmfruits.entries;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import se.kjellstrand.tlmfruits.model.Entry;
import se.kjellstrand.tlmfruits.model.Fruit;
import se.kjellstrand.tlmfruits.model.PostEntry;
import se.kjellstrand.tlmfruits.repo.FruitsDiaryRepository;
import se.kjellstrand.tlmfruits.repo.Resource;

public class EntriesViewModel extends ViewModel {

    @Inject
    public FruitsDiaryRepository fruitsDiaryRepository;

    public EntriesViewModel() {
        fruitsDiaryRepository = new FruitsDiaryRepository();
    }

    public LiveData<Resource<List<Entry>>> getEntries() {
        return fruitsDiaryRepository.getEntries();
    }

    public LiveData<Resource<Entry>> addEntry(PostEntry entry) {
        return fruitsDiaryRepository.addEntry(entry);
    }

    public LiveData<Resource<Entry>> deleteEntry(String entry) {
        return fruitsDiaryRepository.deleteEntry(entry);
    }

    public LiveData<Resource<List<Fruit>>> getFruits() {
        return fruitsDiaryRepository.getFruits();
    }
}
