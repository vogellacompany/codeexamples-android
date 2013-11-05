package com.example.com.vogella.android.actionbar.actionprovider;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
	private ShareActionProvider provider;
	private MenuItem findItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void doSometime() {
		AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "test";
			}

			protected void onPostExecute(String result) {
				findItem.collapseActionView();
				findItem.setActionView(null);
			};
		};
		task.execute("test");

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// case R.id.menu_share:
		// doShare();
		// break;
		case R.id.refresh:
			// Get the ActionProvider
			findItem = item;
			MenuItem setActionView = findItem.setActionView(R.layout.progress);
			findItem.expandActionView();
			doSometime();
		default:
			break;
		}
		return true;
	}

	private void doShare() {
		// Populare the share intent with data
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
		provider.setShareIntent(intent);
	}

}
