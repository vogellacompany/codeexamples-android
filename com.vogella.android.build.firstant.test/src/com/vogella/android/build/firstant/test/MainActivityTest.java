package com.vogella.android.build.firstant.test;

import com.vogella.android.build.firstant.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	public void testDummyCheck() {
		assertTrue(true);
	}
}
