package se.kjellstrand.tlmfruits.entry;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import se.kjellstrand.tlmfruits.model.Entry;
import se.kjellstrand.tlmfruits.model.Fruit;
import se.kjellstrand.tlmfruits.repo.FruitsDiaryRepository;
import se.kjellstrand.tlmfruits.repo.Resource;

public class EntryViewModel extends ViewModel {

    @Inject
    public FruitsDiaryRepository fruitsDiaryRepository;

    public EntryViewModel() {
        fruitsDiaryRepository = new FruitsDiaryRepository();
    }

    public LiveData<Resource<Entry>> getEntry(String id) {
        return fruitsDiaryRepository.getEntry(id);
    }

    public LiveData<Resource<List<Fruit>>> getFruits() {
        return fruitsDiaryRepository.getFruits();
    }

}
