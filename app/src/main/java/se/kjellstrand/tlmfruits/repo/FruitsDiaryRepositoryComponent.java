package se.kjellstrand.tlmfruits.repo;

import dagger.Component;

/**
 * Created by carlemil on 2/5/18.
 */
@Component
public interface FruitsDiaryRepositoryComponent {
    FruitsDiaryRepository getFruitsDiaryRepository();
}
