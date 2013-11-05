package com.example.android.rssfeed;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RssfeedActivity extends Activity implements
		MyListFragment.OnItemSelectedListener {

	private boolean mHasOnePane;
	private String mLastSelectedLink;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssfeed);
		
		mHasOnePane = findViewById(R.id.container) != null;
		
		if (mHasOnePane) {
			FragmentManager fm = getFragmentManager();
			if (fm.findFragmentByTag("list") == null) {
				// add list fragment
				FragmentTransaction trx = fm.beginTransaction();
				trx.add(R.id.container, new MyListFragment(), "list");
				trx.commit();
			}
		} // else, layout handles it

		if (savedInstanceState != null) {
			mLastSelectedLink = savedInstanceState.getString("selectedLink", null);
			onRssItemSelected(mLastSelectedLink);
		}
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mLastSelectedLink != null) {
			outState.putString("selectedLink", mLastSelectedLink);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_refresh:
			if (mHasOnePane) {
				MyListFragment fragment = (MyListFragment) getFragmentManager().findFragmentByTag("list");
				fragment.updateListContent();
			} else {
				MyListFragment fragment = (MyListFragment) getFragmentManager().findFragmentById(R.id.listFragment);
				fragment.updateListContent();
			}
			break;
		case R.id.action_settings:
			Intent intent = new Intent(this, MyPreferenceActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onRssItemSelected(String link) {
		mLastSelectedLink = link;
		
		if (mHasOnePane) {
			
			FragmentManager fm = getFragmentManager();
			DetailFragment detailFragment = (DetailFragment) fm.findFragmentByTag("detail");
			
			if (detailFragment == null) {
				// create and initialize fragment
				detailFragment = new DetailFragment();
				
				// configure link
				Bundle bundle = new Bundle();
				bundle.putString("link", link);
				detailFragment.setArguments(bundle);
				
				// add fragment
				FragmentTransaction trx = fm.beginTransaction();
				trx.replace(R.id.container, detailFragment, "detail");
				trx.addToBackStack(null);
				trx.commit();
				
			} else {
				
				detailFragment.getArguments().putString("link", link);
			}
			
		} else {
			
			DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
			fragment.setText(link);
		}
		
	}
}
