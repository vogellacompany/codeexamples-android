package de.vogella.android.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SearchExampleActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
	}

	public void startSearch(View View) {
		onSearchRequested();
	}

}