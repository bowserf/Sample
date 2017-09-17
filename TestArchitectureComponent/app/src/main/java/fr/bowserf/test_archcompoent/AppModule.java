package fr.bowserf.test_archcompoent;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private UserDao mUserDao;
    private Executor mExecutor;

    public AppModule(UserDao userDao, Executor executor){
        mUserDao = userDao;
        mExecutor = executor;
    }

    @Singleton
    @Provides
    UserRepository provideUserRepository(){
        return new UserRepository(mUserDao, mExecutor);
    }

}
