package se.kjellstrand.tlmfruits.entry;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import se.kjellstrand.tlmfruits.model.Entry;
import se.kjellstrand.tlmfruits.model.Fruit;
import se.kjellstrand.tlmfruits.repo.FruitsDiaryRepository;
import se.kjellstrand.tlmfruits.repo.Resource;

public class EntryViewModel extends ViewModel {

    // TODO @Inject with Dagger 2
    public FruitsDiaryRepository fruitsDiaryRepository;

    public EntryViewModel() {
        fruitsDiaryRepository = new FruitsDiaryRepository();
    }

    public LiveData<Resource<Entry>> getEntry(int id) {
        return fruitsDiaryRepository.getEntry(id);
    }

    public LiveData<Resource<List<Fruit>>> getFruits() {
        return fruitsDiaryRepository.getFruits();
    }

}
