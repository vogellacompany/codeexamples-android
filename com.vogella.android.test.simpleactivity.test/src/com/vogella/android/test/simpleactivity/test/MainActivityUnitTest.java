package com.vogella.android.test.simpleactivity.test;

import android.content.Intent;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import com.vogella.android.test.simpleactivity.MainActivity;

public class MainActivityUnitTest extends
		android.test.ActivityUnitTestCase<MainActivity> {

	private int buttonId;
	private MainActivity activity;

	public MainActivityUnitTest() {
		super(MainActivity.class);
	}
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	@SmallTest
	public void testLayout() {
		
		buttonId = com.vogella.android.test.simpleactivity.R.id.button1;
		assertNotNull(activity.findViewById(buttonId));
		Button view = (Button) activity.findViewById(buttonId);
		assertEquals("Incorrect label of the button", "Start", view.getText());
	}

	@SmallTest
	public void testIntentTriggerViaOnClick() {
		buttonId = com.vogella.android.test.simpleactivity.R.id.button1;
		Button view = (Button) activity.findViewById(buttonId);
		assertNotNull("Button not allowed to be null", view);

		view.performClick();
		
		// TouchUtils cannot be used, only allowed in 
		// InstrumentationTestCase or ActivityInstrumentationTestCase2 
	
		// Check the intent which was started
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent);
		String data = triggeredIntent.getExtras().getString("URL");

		assertEquals("Incorrect data passed via the intent",
				"http://www.vogella.com", data);
	}

	@Override
	protected void tearDown() throws Exception {
		
		super.tearDown();
	}
}
