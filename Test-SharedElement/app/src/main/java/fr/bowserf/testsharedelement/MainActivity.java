package fr.bowserf.testsharedelement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);



        final ImageView image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransitionActivity.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        MainActivity.this, image, getResources().getString(R.string.transition_name));
                // start the new activity
                startActivity(intent, options.toBundle());
            }
        });
    }
}
