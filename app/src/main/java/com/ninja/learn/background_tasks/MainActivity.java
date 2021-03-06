package com.ninja.learn.background_tasks;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBackgroundTask();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startBackgroundTask() {
        try {
            BackgroundUtil.scheduleBackgroundTask(this);
        } catch (Exception e) {
            Log.e(TAG, "startBackgroundTask: ", e);
        }
    }
}