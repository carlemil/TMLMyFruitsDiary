package se.kjellstrand.tlmfruits.repo;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by carlemil on 2/5/18.
 */

@Singleton
@Component
public interface FruitsDiaryRepositoryComponent {
    FruitsDiaryRepository getFruitsDiaryRepository();
}
