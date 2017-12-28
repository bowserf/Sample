package fr.bowserf.testdagger2.other;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import javax.inject.Inject;

import fr.bowserf.testdagger2.R;
import fr.bowserf.testdagger2.config.TestDagger2Application;

public class OtherActivity extends Activity implements OtherContract.View{

    @Inject
    OtherPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_main);

        setupGraph();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    private void setupGraph() {
        OtherComponent otherComponent = TestDagger2Application.getAppComponent(this).provideOtherComponent(new OtherModule(this));
        otherComponent.inject(this);
    }

    @Override
    public void displayStartToast() {
        Toast.makeText(this, "OtherPresenter is well initialized", Toast.LENGTH_SHORT).show();
    }
}
