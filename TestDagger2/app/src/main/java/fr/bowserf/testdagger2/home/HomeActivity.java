package fr.bowserf.testdagger2.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import fr.bowserf.testdagger2.R;
import fr.bowserf.testdagger2.config.TestDagger2Application;
import fr.bowserf.testdagger2.other.OtherActivity;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        setupGraph();

        findViewById(R.id.btn_start_other_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OtherActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    private void setupGraph() {
        HomeComponent build = DaggerHomeComponent.builder()
                .testDagger2ApplicationComponent(TestDagger2Application.getAppComponent(this))
                .homeModule(new HomeModule(this))
                .build();
        build.inject(this);
    }

    @Override
    public void displayStartToast() {
        Toast.makeText(
                this,
                "Activity started and well initialized",
                Toast.LENGTH_SHORT)
                .show();
    }
}
