package fr.bowserf.testdagger2.config;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import fr.bowserf.testdagger2.other.OtherComponent;
import fr.bowserf.testdagger2.other.OtherModule;

@Singleton
@Component(modules = TestDagger2Module.class)
public interface TestDagger2ApplicationComponent {

    void inject(TestDagger2Application app);

    OtherComponent provideOtherComponent(OtherModule module);

    @Component.Builder
    interface Builder {

        TestDagger2ApplicationComponent build();
        @BindsInstance
        Builder context(Context context);
    }

}
