package com.android.example.spinner.test;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.hamcrest.core.AllOf;

import android.test.ActivityInstrumentationTestCase2;

import com.android.example.spinner.SpinnerActivity;

public class SpinnerActivityEspressoTest extends
		ActivityInstrumentationTestCase2<SpinnerActivity> {
	
	 public SpinnerActivityEspressoTest() {
		    super(SpinnerActivity.class);
	 }
	 
	protected void setUp() throws Exception {
		super.setUp();
		getActivity();
	}
	
	public void	testShouldFindSpinner(){
		onView(withId(com.android.example.spinner.R.id.Spinner01)).perform(click());
	}
	public void	testShouldClickOnSpinnerElement(){
		 onData(allOf(is(instanceOf(String.class)), is("Hello")).perform(click()));
		onView(withId(com.android.example.spinner.R.id.Spinner01)).perform(click());
		 
	}
}
