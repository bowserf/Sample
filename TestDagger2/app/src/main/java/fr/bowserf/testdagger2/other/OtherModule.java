package fr.bowserf.testdagger2.other;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class OtherModule {

    private OtherContract.View view;

    public OtherModule(OtherContract.View view){
        this.view = view;
    }

    @Provides
    public OtherPresenter provideOtherPresenter(Context context){
        return new OtherPresenter(view, context);
    }

}
