package fr.bowserf.test_jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "MainActivity";

    private static final int JOB_INFO_ID = 123;

    private static final long SMALLEST_AVAILABLE_PERIODIC = TimeUnit.MILLISECONDS.convert(15, TimeUnit.MINUTES);

    private JobScheduler mJobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);

        findViewById(R.id.btn_add_job_scheduler).setOnClickListener(view -> addJobScheduler());
        findViewById(R.id.btn_remove_job_scheduler).setOnClickListener(view -> removeJobScheduler());
    }

    private void addJobScheduler(){

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
    }

    private void removeJobScheduler(){
        mJobScheduler.cancel(JOB_INFO_ID);
    }
}
