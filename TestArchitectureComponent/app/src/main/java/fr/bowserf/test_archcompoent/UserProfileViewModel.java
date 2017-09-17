package fr.bowserf.test_archcompoent;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;


public class UserProfileViewModel extends ViewModel{

    private UserRepository mUserRepository;

    private LiveData<User> mUser;
    private long mUserId;

    public UserProfileViewModel(UserRepository userRepository){
        mUserRepository = userRepository;
    }

    public void init(long userId){
        if(mUser != null){
            return;
        }
        this.mUser = mUserRepository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return mUser;
    }

    public long getUserId() {
        return mUserId;
    }
}
