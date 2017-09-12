package fr.bowserf.testnotification;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationManagerHelper {

    @SuppressWarnings("unused")
    private static final String TAG = "NotificationManagerHelp";

    private static final String NOTIF_CHANNEL_HIGH_IMPORTANCE = "NotificationManagerHelper.NOTIF_CHANNEL_HIGH_IMPORTANCE";
    private static final String NOTIF_CHANNEL_LOW_IMPORTANCE = "NotificationManagerHelper.NOTIF_CHANNEL_LOW_IMPORTANCE";

    private static final String CHANNEL_GROUP_ID = "NotificationManagerHelper.CHANNEL_GROUP_NAME";

    private static final int LOW_NOTIF_ID = 6405;
    private static final int HIGH_NOTIF_ID = 6406;

    private NotificationManager mNotificationManager;

    private Context mContext;

    public NotificationManagerHelper(Context context){
        mContext = context.getApplicationContext();

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // When a Notification channel is send to the NotificationManager, to change the priority,
            // we need to delete the application and install it again or remove data.

            String channelNameLow = context.getResources().getString(R.string.low_importance_channel_name);
            String channelDescriptionLow = context.getResources().getString(R.string.low_importance_channel_name);
            NotificationChannel notifChannelLowImportance = new NotificationChannel(
                    NOTIF_CHANNEL_LOW_IMPORTANCE,
                    channelNameLow,
                    NotificationManager.IMPORTANCE_LOW); // won't vibrate or light with IMPORTANCE_LOW
            notifChannelLowImportance.setDescription(channelDescriptionLow);
            notifChannelLowImportance.setLightColor(Color.RED); // need to enable lights to show the blink
            notifChannelLowImportance.enableLights(true);

            // we must call setGroup() before submit the chanel to the notification manager
            String groupName = context.getResources().getString(R.string.channel_group_name);
            mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(CHANNEL_GROUP_ID, groupName));
            notifChannelLowImportance.setGroup(CHANNEL_GROUP_ID);

            mNotificationManager.createNotificationChannel(notifChannelLowImportance);

            String channelNameHigh = context.getResources().getString(R.string.high_importance_channel_name);
            String channelDescriptionHigh = context.getResources().getString(R.string.high_importance_channel_name);
            NotificationChannel notifChannelHighImportance = new NotificationChannel(
                    NOTIF_CHANNEL_HIGH_IMPORTANCE,
                    channelNameHigh,
                    NotificationManager.IMPORTANCE_HIGH);
            notifChannelHighImportance.setLightColor(Color.BLUE); // need to enable lights to show the blink
            notifChannelHighImportance.enableLights(true);
            notifChannelHighImportance.setVibrationPattern(new long[]{500, 500, 200, 200, 500, 500}); // change the default pattern of the vibration
            notifChannelHighImportance.setDescription(channelDescriptionHigh);
            //notifChannelHighImportance.setShowBadge(true); // enable by default

            // it's the last notification which decides of the color of the LED

            mNotificationManager.createNotificationChannel(notifChannelHighImportance);
        }
    }

    public void displayLowImportanceNotif(String title, String body){
        Notification notif = new NotificationCompat.Builder(mContext, NOTIF_CHANNEL_LOW_IMPORTANCE)
                .setContentTitle(title)
                .setContentText(body)
                .setLocalOnly(true) // this notification will only be displayed on this device and not a remote one
                .setAutoCancel(true) // notification is automatically canceled when user click on the notification
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        mNotificationManager.notify(LOW_NOTIF_ID, notif);
    }

    public void displayHighImportanceNotif(String title, String body){
        Notification notif = new NotificationCompat.Builder(mContext, NOTIF_CHANNEL_HIGH_IMPORTANCE)
                .setContentTitle(title)
                .setContentText(body)
                .setLocalOnly(true) // this notification will only be displayed on this device and not a remote one
                .setAutoCancel(true) // notification is automatically canceled when user click on the notification
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{200, 200, 200, 200, 200, 200}) // even if we set these values, for Android Oreo and above,
                                                                      // it's values declared for the channel which are taken into account.
                .setLights(Color.BLUE, 2000, 1000) // same for light
                //.setNumber(4) // override number displayed inside shortcut view when long touch on the app icon
                .build();
        mNotificationManager.notify(HIGH_NOTIF_ID, notif);
    }

}
