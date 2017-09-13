package fr.bowserf.testshortcut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SecondLaunchFromShortcutActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "SecondLaunchFromShortcutActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_launch_from_shortcut);
    }
}
