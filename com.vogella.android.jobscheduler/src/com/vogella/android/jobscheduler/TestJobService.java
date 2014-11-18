package com.vogella.android.jobscheduler;

import java.util.LinkedList;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * JobService to be scheduled by the JobScheduler. Requests scheduled with
 * the JobScheduler ultimately land on this service's "onStartJob" method.
 * Currently all this does is write a log entry
 */
public class TestJobService extends JobService {
	private static final String TAG = "SyncService";


	@Override
	public boolean onStartJob(JobParameters params) {
		// We don't do any real 'work' in this sample app. All we'll
		// do is track which jobs have landed on our service, and
		// update the UI accordingly.
		Log.i(TAG, "on start job: " + params.getJobId());
		return true;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		Log.i(TAG, "on stop job: " + params.getJobId());
		return true;
	}

	MainActivity mActivity;
	private final LinkedList<JobParameters> jobParamsMap = new LinkedList<JobParameters>();

	public void setUiCallback(MainActivity activity) {
		mActivity = activity;
	}

}
