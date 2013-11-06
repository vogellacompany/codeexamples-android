package com.vogella.android.test.async.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import com.vogella.android.test.async.MainActivity;
import com.vogella.android.test.async.MainActivity.IJobListener;
import com.vogella.android.test.async.R;

public class AsyncTaskTester extends ActivityUnitTestCase<MainActivity> {

	private MainActivity activity;

	public AsyncTaskTester() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSomeAsynTask () throws Throwable {
	    // create CountDownLatch for which the test can wait.
	    final CountDownLatch latch = new CountDownLatch(1);

	    activity.setListener(new IJobListener() {
			
			@Override
			public void executionDone() {
				latch.countDown();
			}
		});
	    
	    // Execute the async task on the UI thread! THIS IS KEY!
	    runTestOnUiThread(new Runnable() {

	        @Override
	        public void run() {
	        	Button button = (Button) activity.findViewById(R.id.button1);
	        	button.performClick();
	        }
	    });       

	    
	    boolean await = latch.await(30, TimeUnit.SECONDS);

	    assertTrue(await);
	}

}
