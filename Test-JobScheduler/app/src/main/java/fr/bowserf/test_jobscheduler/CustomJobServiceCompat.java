package fr.bowserf.test_jobscheduler;


import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class CustomJobServiceCompat extends JobService {

    @SuppressWarnings("unused")
    private static final String TAG = "CustomJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this, "Do some compat work !", Toast.LENGTH_SHORT).show();

        jobFinished(jobParameters, true); // notify that the job is finished
                                          // second parameter is to reschedule the job

        return false; // return true if the service needs to process the work on a separate thread.
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
