package fr.bowserf.test_broadcast_oreo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {

    @SuppressWarnings("unused")
    private static final String TAG = "MyBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        Toast.makeText(context, "Event received !", Toast.LENGTH_SHORT).show();

        Intent serviceIntent = new Intent(context, MyIntentService.class);
        context.startService(serviceIntent);
        //context.startForegroundService(serviceIntent);

        //MyJobIntentService.enqueueWork(context);
    }
}
