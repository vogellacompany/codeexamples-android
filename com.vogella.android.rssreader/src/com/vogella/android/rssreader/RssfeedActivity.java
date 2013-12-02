package com.vogella.android.rssreader;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * RSS Feed Activity is capable to handle two layouts:
 *  - one pane layout
 *  - two pane layout
 * 
 * In one pane layout both the list and detail fragments are shown in a single
 * container replacing each other.
 * 
 * In two pane layout list fragment takes left container, while details view 
 * takes right one.
 * 
 * @author sergej shafarenka
 */
public class RssfeedActivity extends Activity implements
		RssfeedListFragment.OnItemSelectedListener {

	public static final String TAG_LIST = "list";
	public static final String TAG_DETAIL = "detail";
	public static final String EXTRA_URL = "url";
	
	private boolean isOnePaneLayout;
	private String selectedRssItemUrl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssfeed);
		
		// detect layout
		isOnePaneLayout = findViewById(R.id.right_container) == null;
		
		// read last selected RSS item URL, if such
		if (savedInstanceState != null) {
			selectedRssItemUrl = savedInstanceState.getString(EXTRA_URL);
		} else {
			// check whether we were called with a URL extra
			String url = getIntent().getStringExtra(EXTRA_URL);
			if (url != null) {
				selectedRssItemUrl = url;
			}
		}
		
		addListFragment();

		if (selectedRssItemUrl != null) {
			if (isOnePaneLayout) {
				showDetailFragment(R.id.left_container, selectedRssItemUrl, true);
			} else {
				getFragmentManager().popBackStackImmediate();
				showDetailFragment(R.id.right_container, selectedRssItemUrl, false);
			}
		}
		
	}

	private void addListFragment() {
		
		// find list fragment first
		FragmentManager fm = getFragmentManager();
		RssfeedListFragment listFragment = 
				(RssfeedListFragment) fm.findFragmentByTag(TAG_LIST);
		
		if (listFragment == null) {
			// create new list fragment
			listFragment = new RssfeedListFragment();
		
			// add list fragment to the layout
			fm.beginTransaction()
				.add(R.id.left_container, listFragment, TAG_LIST)
				.commit();

			// start loading data
			listFragment.updateListContent();
		}

	}
	
	private void showDetailFragment(int containerId, String rssItemUrl, boolean addToBackStack) {
		
		// find detail fragment first
		FragmentManager fm = getFragmentManager();
		RssfeedDetailFragment detailFragment =
				(RssfeedDetailFragment) fm.findFragmentByTag(TAG_DETAIL);

		if (detailFragment == null) {
			// create new detail fragment
			detailFragment = RssfeedDetailFragment.instantiate(rssItemUrl);
			
			// add fragment to the layout
			addDetailFragment(detailFragment, containerId, addToBackStack, fm);
			
		} else if (detailFragment.getId() != containerId) {
				
			// remove fragment from old container
			fm.beginTransaction().remove(detailFragment).commit();
			fm.executePendingTransactions();
			
			// add fragment to the new container
			addDetailFragment(detailFragment, containerId, addToBackStack, fm);
			
		} // else, fragment is already at the right place
		
		detailFragment.setUrl(rssItemUrl);
		
	}
	
	private static void addDetailFragment(RssfeedDetailFragment detailFragment, int containerId, boolean addToBackStack, FragmentManager fm) {
		FragmentTransaction trx = fm.beginTransaction();
		if (addToBackStack) {
			trx.replace(containerId, detailFragment, TAG_DETAIL);
			trx.addToBackStack(null);
		} else {
			trx.add(containerId, detailFragment, TAG_DETAIL);
		}
		trx.commit();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (selectedRssItemUrl != null) {
			outState.putString(EXTRA_URL, selectedRssItemUrl);
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
			/*
			if (isOnePaneLayout) {
				MyListFragment fragment = (MyListFragment) 
						getFragmentManager().findFragmentByTag("list");
				fragment.updateListContent();
			} else {
				MyListFragment fragment = (MyListFragment) 
						getFragmentManager().findFragmentById(R.id.list);
				fragment.updateListContent();
			}
			*/
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
		
		if (isOnePaneLayout) {
			showDetailFragment(R.id.left_container, link, true);
		} else {
			showDetailFragment(R.id.right_container, link, false);
		}
		
		// store selected URL
		selectedRssItemUrl = link;
	}
}
