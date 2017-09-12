package fr.bowserf.testnotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManagerHelper notificationManagerHelper = new NotificationManagerHelper(this);

        Button btnCreateNotifLowImportance = findViewById(R.id.btn_create_notif_low_importance);
        btnCreateNotifLowImportance.setOnClickListener(view -> {
            String title = getResources().getString(R.string.low_importance_notif_title);
            String content = getResources().getString(R.string.low_importance_notif_content);
            notificationManagerHelper.displayLowImportanceNotif(title, content);
        });

        Button btnCreateNotifHighImportance = findViewById(R.id.btn_create_notif_high_importance);
        btnCreateNotifHighImportance.setOnClickListener(view -> {
            String title = getResources().getString(R.string.high_importance_notif_title);
            String content = getResources().getString(R.string.high_importance_notif_content);
            notificationManagerHelper.displayHighImportanceNotif(title, content);
        });
    }
}
