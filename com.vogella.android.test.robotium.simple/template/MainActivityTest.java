package com.vogella.android.test.robotium.simple.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import com.vogella.android.test.robotium.simple.MainActivity;
import com.vogella.android.test.robotium.simple.NextActivity;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public MainActivityTest() {
		super(MainActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	public void testButtonClickShouldUpdateText() throws Exception {
		// check that we have the right activity
	}

	public void testActivityFlow() throws Exception {
		// check that we have the right activity
	}
}