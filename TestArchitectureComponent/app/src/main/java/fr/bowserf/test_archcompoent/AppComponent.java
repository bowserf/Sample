package fr.bowserf.test_archcompoent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)

public interface AppComponent {

    void inject(UserProfileViewModel viewModel);

    void inject(MainActivity activity);

    UserRepository provideUserRepository();

}
