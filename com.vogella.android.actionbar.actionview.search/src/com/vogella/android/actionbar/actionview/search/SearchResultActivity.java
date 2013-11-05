package com.vogella.android.actionbar.actionview.search;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SearchResultActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
	}
}
