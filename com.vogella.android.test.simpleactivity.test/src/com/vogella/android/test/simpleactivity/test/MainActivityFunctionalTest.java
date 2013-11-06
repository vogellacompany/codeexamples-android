package com.vogella.android.test.simpleactivity.test;

import android.app.Instrumentation.ActivityMonitor;
import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vogella.android.test.simpleactivity.MainActivity;
import com.vogella.android.test.simpleactivity.R;
import com.vogella.android.test.simpleactivity.SecondActivity;

public class MainActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	protected static final String INPUT = "FOR TESTING";
	private MainActivity activity;

	public MainActivityFunctionalTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}

	public void testStartSecondActivity() throws Exception {
		final EditText editText = (EditText) activity
				.findViewById(R.id.editText1);
	
		
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				editText.setText(INPUT);
			}
		});
		// Add monitor to check for the second activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				SecondActivity.class.getName(), null, false);

		// Find button and click it
		Button view = (Button) activity.findViewById(R.id.button1);
		TouchUtils.clickView(this, view);

		// To click on a click, e.g. in a listview
		// listView.getChildAt(0);

		// Wait 2 seconds for the start of the activity
		SecondActivity startedActivity = (SecondActivity) monitor
				.waitForActivityWithTimeout(2000);
		assertNotNull(startedActivity);

		// Search for the textView
		TextView textView = (TextView) startedActivity
				.findViewById(R.id.resultText);

		// Check that the TextView is on the screen
		ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				textView);
		// Validate the text on the TextView
		assertEquals("Text incorrect", INPUT, textView.getText().toString());
		// Press back and click again
		this.sendKeys(KeyEvent.KEYCODE_BACK);
		TouchUtils.clickView(this, view);
	}

}
