package fr.bowserf.test_archcompoent;

import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final UserDao mUserDao;
    private final Executor mExecutor;

    @Inject
    public UserRepository(UserDao userDao, Executor executor) {
        mUserDao = userDao;
        mExecutor = executor;
    }

    public LiveData<User> getUser(long userId) {
        refreshUser(userId);

        return mUserDao.load(userId);
    }

    private void refreshUser(long userId) {
        mExecutor.execute(() -> {
            Looper.prepare();
            // generate new user
            new Handler().postDelayed(() -> {
                User user = new User(userId, "Pepito");
                mUserDao.save(user);
            }, 2000);

            Looper.loop();
        });
    }

}
