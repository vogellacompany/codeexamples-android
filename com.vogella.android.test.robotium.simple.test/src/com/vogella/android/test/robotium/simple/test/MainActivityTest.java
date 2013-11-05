package com.vogella.android.test.robotium.simple.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.jayway.android.robotium.solo.Solo;
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

	public void testExercise01() throws Exception {
		// Check that we have the right activity
		solo.assertCurrentActivity("wrong activity", MainActivity.class);
		solo.clickOnButton("Update Text");
		solo.searchText("Current time");
	}

	public void testExercise02() throws Exception {
		// Check that we have the right activity
		solo.assertCurrentActivity("wrong activity", MainActivity.class);
		solo.clickOnButton("Start Activity");
		solo.assertCurrentActivity("wrong activity", NextActivity.class);
		EditText text = (EditText) solo
				.getView(com.vogella.android.test.robotium.simple.R.id.editText1);
		assertNotNull(text);
		solo.enterText(text, "Grass");
	}
}