package fr.bowserf.testdagger2.config;

import android.app.Application;
import android.content.Context;

public class TestDagger2Application extends Application{

    private TestDagger2ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupGraph();
    }

    private void setupGraph() {
        appComponent = DaggerTestDagger2ApplicationComponent.builder()
                .context(this)
                .build();
        appComponent.inject(this);
    }

    public static TestDagger2ApplicationComponent getAppComponent(Context context){
        return ((TestDagger2Application)context.getApplicationContext()).getAppComponent();
    }

    public TestDagger2ApplicationComponent getAppComponent() {
        return appComponent;
    }
}
