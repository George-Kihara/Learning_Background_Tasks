package com.ninja.learn.background_tasks;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class BackgroundUtil {
    private static final String TAG = "BackgroundUtil";

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleBackgroundTask(Context context) {
        try {
            ComponentName componentName = new ComponentName(context, MyJobService.class);
            JobInfo.Builder builder = new JobInfo.Builder(0, componentName);
            long timeInterval = 3 * 1000;

            builder.setPersisted(true);
            builder.setMinimumLatency(timeInterval); // waiting time
            builder.setOverrideDeadline(timeInterval); // maximum delay
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

            JobScheduler jobScheduler = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                jobScheduler = context.getSystemService(JobScheduler.class);
            }
            jobScheduler.schedule(builder.build());
        } catch (Exception e) {
            Log.e(TAG, "scheduleBackgroundTask: ", e);
        }
    }

}
