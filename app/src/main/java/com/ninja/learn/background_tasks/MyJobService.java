package com.ninja.learn.background_tasks;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e(TAG, "onStartJob: THIS IS THE BEST CLASS!!");

        // reschedule
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
        long timeInterval = 3 * 1000;

        builder.setPersisted(true);
        builder.setMinimumLatency(timeInterval); // waiting time
        builder.setOverrideDeadline(timeInterval); // maximum delay
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            jobScheduler = this.getSystemService(JobScheduler.class);
        }
        jobScheduler.schedule(builder.build());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e(TAG, "onStopJob: BYE!!");
        return true;
    }
}
