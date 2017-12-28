package fr.bowserf.testdagger2.home;

import dagger.Component;
import fr.bowserf.testdagger2.GenericScope;
import fr.bowserf.testdagger2.config.TestDagger2ApplicationComponent;

@GenericScope(component = HomeComponent.class)
@Component(modules = HomeModule.class, dependencies = TestDagger2ApplicationComponent.class)
public interface HomeComponent {

    void inject(HomeActivity activity);

    HomePresenter provideHomePresenter();

}
