package fr.bowserf.testdagger2.home;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private HomeContract.View view;

    public HomeModule(HomeContract.View view){
        this.view = view;
    }

    @Provides
    public HomePresenter provideHomePresenter(){
        return new HomePresenter(view);
    }

}
