package se.kjellstrand.tlmfruits.entries;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import se.kjellstrand.tlmfruits.repo.FruitsDiaryRepository;
import se.kjellstrand.tlmfruits.entries.model.Entry;
import se.kjellstrand.tlmfruits.entries.model.PostEntry;
import se.kjellstrand.tlmfruits.repo.Resource;

public class EntriesViewModel extends ViewModel {

    // TODO @Inject with Dagger 2
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

    public LiveData<Entry> deleteEntry(int entry){
        return fruitsDiaryRepository.deleteEntry(entry);
    }
}
