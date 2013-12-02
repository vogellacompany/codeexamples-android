package com.vogella.android.test.fragment.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.vogella.android.test.fragment.ItemListActivity;

public class MyFragmentTest extends ActivityInstrumentationTestCase2<ItemListActivity> {

	public MyFragmentTest() {
		super(ItemListActivity.class);
	}
	
	
	protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	    		ItemListActivity.class);
	    
	    getActivity();
	    startActivity(intent, null, null);

//	    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//	    mFragmentWelcome = (FragmentWelcome) fragmentManager.findFragmentByTag(FragmentWelcome.TAG);
//	    if (mFragmentWelcome == null) {

//	        mFragmentWelcome= FragmentWelcome.newInstance();
//	        fragmentManager
//	                .beginTransaction()
//	                .replace(android.R.id.content, mFragmentWelcome, FragmentWelcome.TAG)
//	                .commit();
//	    }
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
