package com.vogella.android.rssreader.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import android.content.pm.ActivityInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.ListAdapter;

import com.vogella.android.rssreader.RssfeedActivity;
import com.vogella.android.rssreader.RssfeedDetailFragment;
import com.vogella.android.rssreader.RssfeedListFragment;

public class RssfeedActivityTest 
	extends ActivityInstrumentationTestCase2<RssfeedActivity> {

	private RssfeedActivity mActivity;
	private RssfeedDetailFragment mDetailFragment;
	
	private RssfeedListFragment mListFragment;
	private ListAdapter mListAdapter;

	public RssfeedActivityTest() {
		super(RssfeedActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// disable touch, enable key events
		// TODO disable touch mode using setActivityInitialTouchMode() 

		mActivity = getActivity();
		
		// get list fragment
		mListFragment = 
				(RssfeedListFragment) mActivity
				.getFragmentManager()
				.findFragmentByTag(RssfeedActivity.TAG_LIST);
		mListAdapter = mListFragment.getListAdapter();
		
		// get detail fragment
		mDetailFragment = 
				(RssfeedDetailFragment) mActivity
				.getFragmentManager()
				.findFragmentByTag(RssfeedActivity.TAG_DETAIL);
	}

	/** Test initial activity state. */
	public void testPreConditions() {
		// TODO assert mListFragment is not null
		// TODO assert mListAdapter is not null
		// TODO assert mDetailFragment is null
	}

	/** Test the list to be loaded */
	public void testListLoaded() throws InterruptedException {
		waitForList();
		assertTrue(mListAdapter.getCount() > 0);
	}

	/** Test details fragment gets shown on item click */
	public void testListClicked() throws InterruptedException {
		waitForList();
		getInstrumentation()
		// select second item in the list
		// TODO send KeyEvent.KEYCODE_DPAD_DOWN and then KeyEvent.KEYCODE_DPAD_CENTER keys
		// TODO call waitForIdleSync() of instrumentation instance
		
		// get detail fragment
		mDetailFragment = 
				(RssfeedDetailFragment) mActivity
				.getFragmentManager()
				.findFragmentByTag(RssfeedActivity.TAG_DETAIL);

		// TODO assert mDetailFragment is not null
		// TODO assert mDetailFragment.getArguments().getString("url") is not null
	}
	
	/** Test selected list position on orientation change */
	public void testListPositionSaved() throws Exception {
		
		waitForList();
		
		// select an item
		// TODO send KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN and then KEYCODE_DPAD_CENTER keys
		// TODO call waitForIdleSync() of instrumentation instance
		
		// store old list fragment
		final RssfeedListFragment listFragment = mListFragment;
		
		// rotate device
		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getInstrumentation().waitForIdleSync();
		
		// reinitialize activity and fragments
		mActivity = getActivity();
		setUp();
		
		waitForList();

		// assert item positions
		getInstrumentation().runOnMainSync(new Runnable() {
			@Override public void run() {
				// compare old and new positions
				// TODO assert listFragment.getSelectedItemPosition() 
				//      equals to mListFragment.getSelectedItemPosition() 
			}
		});
		
	}
	
	/** Utility method waiting for list to be loaded */
	private void waitForList() throws InterruptedException {
		
		// create waiting object
		CountDownLatch signal = new CountDownLatch(1);
		
		// get counter
		int count = mListAdapter.getCount();
		
		// continue, if we have items
		if (count > 0) signal.countDown();
		
		// otherwise, wait for max. 2 seconds  
		signal.await(500, TimeUnit.MILLISECONDS);
	}
	
}
