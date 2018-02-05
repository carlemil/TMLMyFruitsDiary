package se.kjellstrand.tlmfruits.repo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlemil on 2/5/18.
 */
@Module
public class FruitsDiaryRepositoryModule {
    @Provides
    @Singleton
    FruitsDiaryRepository providesFruitsDiaryRepository(){
        return new FruitsDiaryRepository();
    }
}
