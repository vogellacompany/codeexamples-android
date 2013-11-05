package com.vogella.android.test.helloworld.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.vogella.android.test.helloworld.MainActivity;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	// Activity of the Target application
	MainActivity mainActivity;

	// TextView of the MainActivity to be tested
	TextView tvHello;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Starts the MainActivity of the target application
		startActivity(new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class), null, null);

		// Getting a reference to the MainActivity of the target application
		mainActivity = (MainActivity) getActivity();

		// Getting a reference to the TextView of the MainActivity of the target
		// application
		tvHello = (TextView) mainActivity
				.findViewById(com.vogella.android.test.helloworld.R.id.hello_world);

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@SmallTest
	public void testHello() {
		// The actual text displayed in the textview
		String actual = tvHello.getText().toString();

		// The expected text to be displayed in the textview
		String expected = "Hello world!";

		// Check whether both are equal, otherwise test fails
		assertEquals(expected, actual);
	}
}
