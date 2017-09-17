package fr.bowserf.test_archcompoent;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    @Inject
    UserProfileViewModelFactory mUserProfileFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "database_name").build();

        AppComponent appComponent  = DaggerAppComponent.builder()
                .appModule(new AppModule(myDatabase.userDao(), AsyncTask.THREAD_POOL_EXECUTOR))
                .build();

        appComponent.inject(this);

        UserProfileViewModel userProfileViewModel = ViewModelProviders.of(this, mUserProfileFactory).get(UserProfileViewModel.class);
        userProfileViewModel.init(12346789);

        final TextView tvUserName = findViewById(R.id.tv_user_name);

        // LiveData is lifecycle aware, it will not invoke the callback unless the activity is active
        // We don't need to override onStop() or onDestroy() to unsubscribe, to automatic
        userProfileViewModel.getUser().observe(this, user -> {
            if(user != null) {
                tvUserName.setText(user.getName());
            }
        });
    }
}