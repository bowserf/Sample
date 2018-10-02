package fr.bowserf.test_broadcast_oreo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.JobIntentService;
import android.util.Log;

public class MyJobIntentService extends JobIntentService {

    @SuppressWarnings("unused")
    private static final String TAG = "UpdateJobIntentService";

    static final int JOB_ID = 1000;

    static void enqueueWork(Context context) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, new Intent());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleWork(Intent intent) {
        Log.d(TAG, "onHandleWork before sleep");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }
        Log.d(TAG, "onHandleWork after sleep");
    }

    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG, "onStopCurrentWork");
        return false;
    }
}