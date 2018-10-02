package fr.bowserf.test_broadcast_oreo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    private static final String NOTIF_CHANNEL_DEFAULT_IMPORTANCE = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_register_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBroadcastReceiver();
            }
        });
        findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMyService();
            }
        });

        String channelNameDefault = "Channel name";
        NotificationChannel notifChannel = new NotificationChannel(
                NOTIF_CHANNEL_DEFAULT_IMPORTANCE,
                channelNameDefault,
                NotificationManager.IMPORTANCE_DEFAULT);
        notifChannel.setLightColor(Color.RED);
        notifChannel.enableLights(true);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(notifChannel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void registerBroadcastReceiver(){
        // Application need to be in background to keep the registration of its broadcast receiver
        // It's not a real alternative to the previous behavior
        registerReceiver(new MyBroadcast(), new IntentFilter(Intent.ACTION_USER_PRESENT));
    }

    private void startMyService(){
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

}
