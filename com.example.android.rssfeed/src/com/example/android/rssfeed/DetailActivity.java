package com.example.android.rssfeed;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

public class DetailActivity extends Activity {
	public static final String EXTRA_URL = "url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Application application = getApplication();

		// Need to check if Activity has been switched to landscape mode
		// If yes, finished and go back to the start Activity
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		setContentView(R.layout.activity_detail);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String url = extras.getString(EXTRA_URL);
			DetailFragment detailFragment = (DetailFragment) getFragmentManager()
					.findFragmentById(R.id.detailFragment);
			detailFragment.setText(url);
		}
	}
}