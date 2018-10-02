package fr.bowserf.test_broadcast_oreo;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    @SuppressWarnings("unused")
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "Service created");

        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }

        Notification notif = new Notification.Builder(this, "id")
                .setContentTitle("Foreground service notification")
                .setContentText("Pouette")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        startForeground(42, notif);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service destroyed");
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();
    }
}
