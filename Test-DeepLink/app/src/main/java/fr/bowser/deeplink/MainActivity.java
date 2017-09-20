package fr.bowser.deeplink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// https://developer.android.com/training/app-links/deep-linking.html
// https://developer.android.com/studio/write/app-link-indexing.html

//TODO associate with website by adding a file on server

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
