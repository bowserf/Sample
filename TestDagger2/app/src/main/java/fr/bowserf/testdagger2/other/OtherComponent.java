package fr.bowserf.testdagger2.other;

import dagger.Subcomponent;
import fr.bowserf.testdagger2.GenericScope;

@GenericScope(component = OtherComponent.class)
@Subcomponent(modules = OtherModule.class)
public interface OtherComponent {

    void inject(OtherActivity activity);

    OtherPresenter provideOtherPresenter();

}
