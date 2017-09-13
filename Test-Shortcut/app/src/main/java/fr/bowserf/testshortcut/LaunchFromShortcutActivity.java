package fr.bowserf.testshortcut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LaunchFromShortcutActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "LaunchFromShortcutActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_from_shortcut);
    }
}
