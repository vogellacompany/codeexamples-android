package com.android.example.spinner.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.KeyEvent;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.example.spinner.SpinnerActivity;

public class SpinnerActivityTest extends
		ActivityInstrumentationTestCase2<SpinnerActivity> {
	public static final int TEST_STATE_DESTROY_POSITION = 2;
	public static final String TEST_STATE_DESTROY_SELECTION = "Earth";

	public static final int TEST_STATE_PAUSE_POSITION = 4;
	public static final String TEST_STATE_PAUSE_SELECTION = "Jupiter";

	private SpinnerActivity mActivity;
	private Spinner mSpinner;
	private SpinnerAdapter mPlanetData;

	public SpinnerActivityTest() {
		super(SpinnerActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		mActivity = getActivity();

		mSpinner = (Spinner) mActivity
				.findViewById(com.android.example.spinner.R.id.Spinner01);

		mPlanetData = mSpinner.getAdapter();

	} // end of setUp() method definition

	public void testPreConditions() {
		assertTrue(mSpinner.getOnItemSelectedListener() != null);
		assertTrue(mPlanetData != null);
		assertTrue(mPlanetData.getCount() > 0);
	} // end of testPreConditions() method definition

	public void testSpinnerSelection() {
		mActivity.runOnUiThread(new Runnable() {
			public void run() {
				mSpinner.requestFocus();
				mSpinner.setSelection(0);
			}
		});
		int count = mPlanetData.getCount();
		int testposition = count % 2;
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		for (int i = 1; i <= testposition; i++) {
			this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
		}

		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);

		// Ensure that the text is not empty
		int mPos = mSpinner.getSelectedItemPosition();
		String mSelection = (String) mSpinner.getItemAtPosition(mPos);

		// Compare selection in the Spinner with the text view
		TextView resultView = (TextView) mActivity
				.findViewById(com.android.example.spinner.R.id.SpinnerResult);

		String resultText = (String) resultView.getText();

		assertEquals(resultText, mSelection);
	}

	public void testStateTerminate() {
		mActivity.setSpinnerPosition(TEST_STATE_DESTROY_POSITION);
		mActivity.setSpinnerSelection(TEST_STATE_DESTROY_SELECTION);

		// Destroy the activity
		mActivity.finish();
		// Restart
		mActivity = this.getActivity();

		int currentPosition = mActivity.getSpinnerPosition();
		String currentSelection = mActivity.getSpinnerSelection();
		assertEquals(TEST_STATE_DESTROY_POSITION, currentPosition);
		assertEquals(TEST_STATE_DESTROY_SELECTION, currentSelection);

	}

	@UiThreadTest
	public void testStatePause() {
		Instrumentation mInstr = this.getInstrumentation();
		mActivity.setSpinnerPosition(TEST_STATE_PAUSE_POSITION);
		mActivity.setSpinnerSelection(TEST_STATE_PAUSE_SELECTION);

		//
		mInstr.callActivityOnPause(mActivity);
		mActivity.setSpinnerPosition(0);
		mActivity.setSpinnerSelection("");

		mInstr.callActivityOnResume(mActivity);
		int currentPosition = mActivity.getSpinnerPosition();
		String currentSelection = mActivity.getSpinnerSelection();

		assertEquals(TEST_STATE_PAUSE_POSITION, currentPosition);
		assertEquals(TEST_STATE_PAUSE_SELECTION, currentSelection);
	}

}
