package de.vogella.android.search;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SearchableActivity extends ListActivity {

	private String[] values;
	private ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		values = new String[] { "Android", "Android2", "Andere", "iPhone",
				"Windows7" };
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1);
		setListAdapter(adapter);
		performSearch();

	}

	@Override
	protected void onNewIntent(Intent intent) {
		adapter.clear();
		setIntent(intent);
		performSearch();
	}

	private void performSearch() {
		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			findMatches(query);
			setTitle("Search results for: " + query);
		}

	}

	private void findMatches(String query) {
		for (String s : values) {
			if (s.toLowerCase().contains(query.toLowerCase())) {
				adapter.add(s);
			}
		}
	}

}
