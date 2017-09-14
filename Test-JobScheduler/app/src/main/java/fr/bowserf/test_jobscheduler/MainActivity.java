package fr.bowserf.test_jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    private static final int JOB_INFO_ID = 123;
    private static final String JOB_INFO_COMPAT_ID = "job_info_compat_id";

    private static final long SMALLEST_AVAILABLE_PERIODIC = TimeUnit.MILLISECONDS.convert(15, TimeUnit.MINUTES);

    // Availiable since Lollipop
    private JobScheduler mJobScheduler;
    // Compatible since API 9
    private FirebaseJobDispatcher mDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        }else {
            mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        }

        findViewById(R.id.btn_add_job_scheduler).setOnClickListener(view -> addJobScheduler());
        findViewById(R.id.btn_remove_job_scheduler).setOnClickListener(view -> removeJobScheduler());
    }

    private void addJobScheduler(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobInfo jobInfo = new JobInfo.Builder(JOB_INFO_ID, new ComponentName(this, CustomJobService.class))
                    .setPersisted(true) // Set whether or not to persist this job across device reboots.
                    // Requires the RECEIVE_BOOT_COMPLETED permission.
                    //.setPeriodic(SMALLEST_AVAILABLE_PERIODIC)
                    //.setRequiresCharging(false)
                    //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    //.setRequiresBatteryNotLow(false)
                    .setMinimumLatency(1000 * 10)
                    .setOverrideDeadline(1000 * 15) // even if conditions are not met, the service will be called
                    .build();

            mJobScheduler.schedule(jobInfo);
        }else{
            Bundle myExtrasBundle = new Bundle();
            myExtrasBundle.putString("some_key", "some_value");

            Job myJob = mDispatcher.newJobBuilder()
                    // the JobService that will be called
                    .setService(CustomJobServiceCompat.class)
                    // uniquely identifies the job
                    .setTag(JOB_INFO_COMPAT_ID)
                    .setRecurring(true)
                    // persist past a device reboot
                    .setLifetime(Lifetime.FOREVER)
                    // start between 0 and 20 seconds from now
                    .setTrigger(Trigger.executionWindow(0, 20))
                    // overwrite an existing job with the same tag
                    .setReplaceCurrent(true)
                    // retry with exponential backoff
                    .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                    // constraints that need to be satisfied for the job to run
                    .setConstraints(
                            // only run on an unmetered network
                            Constraint.ON_UNMETERED_NETWORK,
                            // only run when the device is charging
                            Constraint.DEVICE_CHARGING
                    )
                    .setExtras(myExtrasBundle)
                    .build();

            mDispatcher.mustSchedule(myJob);
        }
    }

    private void removeJobScheduler(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mJobScheduler.cancel(JOB_INFO_ID);
        }else{
            mDispatcher.cancel(JOB_INFO_COMPAT_ID);
        }
    }
}
