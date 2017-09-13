package fr.bowserf.testshortcut;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.pm.ShortcutInfoCompat;
import android.support.v4.content.pm.ShortcutManagerCompat;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    private static final String SHORTCUT_OPEN_GOOGLE_ID = "dynamic_shortcut_open_google";
    private static final String SHORTCUT_OPEN_ANDROID_DEV_ID = "dynamic_shortcut_open_android_dev";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_add_shortcuts).setOnClickListener(view -> createDynamicShortcut());
        findViewById(R.id.btn_remove_shortcuts).setOnClickListener(view -> deleteDynamicShortcuts());
        findViewById(R.id.btn_add_pinned_shortcuts).setOnClickListener(view -> createPinnedShortcut());
    }

    private void createPinnedShortcut() {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            // Assumes there's already a shortcut with the ID SHORTCUT_OPEN_GOOGLE_ID.
            // The shortcut must be enabled.
            // Label and intent are mandatory to create a ShortcutInfoCompat
            // With the same ID than an existing shortcut, on Android Oreo, it's label and intent
            // define in the already existing shortcut which will be used. On Android Nougat and
            // before, it will be this label and this intent.
            ShortcutInfoCompat pinShortcutInfo = new ShortcutInfoCompat.Builder(this, SHORTCUT_OPEN_GOOGLE_ID)
                    .setShortLabel("Shortcut name for Nougat and before")
                    .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_launcher_round)) // Need to define an icon if it's the first shortcut with this ID to avoid a crash
                    /*.setIntent(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/")))*/
                    .setIntent(new Intent(this, LaunchFromShortcutActivity.class))
                    .build();

            // Create the PendingIntent object only if your app needs to be notified
            // that the user allowed the shortcut to be pinned. Note that, if the
            // pinning operation fails, your app isn't notified. We assume here that the
            // app has implemented a method called createShortcutResultIntent() that
            // returns a broadcast intent.
            /*Intent pinnedShortcutCallbackIntent = ShortcutManagerCompat.createShortcutResultIntent(this, pinShortcutInfo);

            // Configure the intent so that your app's broadcast receiver gets
            // the callback successfully.
            PendingIntent successCallback = PendingIntent.getBroadcast(
                    this,
                    456,
                    pinnedShortcutCallbackIntent,
                    0);*/

            ShortcutManagerCompat.requestPinShortcut(
                    this,
                    pinShortcutInfo,
                    //successCallback.getIntentSender());
                    null);
        }
    }

    private void createDynamicShortcut() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo shortcut1 = new ShortcutInfo.Builder(this, SHORTCUT_OPEN_GOOGLE_ID)
                .setShortLabel("Open google")
                .setLongLabel("Open google web site")
                .setDisabledMessage("Open google is disabled") // message displayed if shortcut1 is disabled
                .setRank(2) // dynamic shortcut will be display in the second place
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher)) // if icon is not set, it takes the icon app
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/"))) // Dynamic shortcuts can be published with any set of Intent flags.
                                                               // It's not the case of static shortcuts !!!
                .build();

        ShortcutInfo shortcut2 = new ShortcutInfo.Builder(this, SHORTCUT_OPEN_ANDROID_DEV_ID)
                .setShortLabel("Open Android developper")
                .setLongLabel("Open Android developper web site")
                .setRank(1) // dynamic shortcut will be display at the top of the popup (first place)
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://developer.android.com/index.html")))
                .build();

        // An app can't have more than 5 static and dynamic shortcut
        // There is no limit to the number of pinned shortcuts
        // When a dynamic shortcut is pinned, even when the publisher removes it as a dynamic
        // shortcut, the pinned shortcut is still visible and lanchable.

        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut1, shortcut2));
    }

    private void deleteDynamicShortcuts(){
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        //shortcutManager.removeDynamicShortcuts(Arrays.asList(SHORTCUT_OPEN_GOOGLE_ID)); // remove only one shortcut
        shortcutManager.removeAllDynamicShortcuts();
    }
}
