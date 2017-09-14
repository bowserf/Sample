package fr.bowserf.test_jobscheduler;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class CustomJobService extends JobService {

    @SuppressWarnings("unused")
    private static final String TAG = "CustomJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this, "Do some work !", Toast.LENGTH_SHORT).show();

        jobFinished(jobParameters, true); // notify that the job is finished
                                          // second parameter is to reschedule the job

        return false; // return true if the service needs to process the work on a separate thread.
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
