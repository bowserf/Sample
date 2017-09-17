package fr.bowserf.test_archcompoent;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class UserProfileViewModelFactory implements ViewModelProvider.Factory {

    private final UserRepository mUserRepository;

    @Inject
    public UserProfileViewModelFactory(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public UserProfileViewModel create(Class modelClass) {
        return new UserProfileViewModel(mUserRepository);
    }
}
