package com.vogella.android.test.simpleactivity.test;

import com.vogella.android.test.simpleactivity.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public MainActivityTest() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
		// Wait for activity: 'com.vogella.android.test.simpleactivity.MainActivity'
		solo.waitForActivity(com.vogella.android.test.simpleactivity.MainActivity.class, 2000);
		// Click on Empty Text View
		solo.clickOnView(solo.getView(com.vogella.android.test.simpleactivity.R.id.editText1));
		// Enter the text: 'kkk'
		solo.clearEditText((android.widget.EditText) solo.getView(com.vogella.android.test.simpleactivity.R.id.editText1));
		solo.enterText((android.widget.EditText) solo.getView(com.vogella.android.test.simpleactivity.R.id.editText1), "kkk");
		// Click on Start
		solo.clickOnView(solo.getView(com.vogella.android.test.simpleactivity.R.id.button1));
		// Wait for activity: 'com.vogella.android.test.simpleactivity.SecondActivity'
		assertTrue("com.vogella.android.test.simpleactivity.SecondActivity is not found!", solo.waitForActivity(com.vogella.android.test.simpleactivity.SecondActivity.class));
	}
}
