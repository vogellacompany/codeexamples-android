package com.vogella.android.mockito.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	/*
	 * Get Application and display data
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView view = (TextView) findViewById(R.id.target);
		MyApp application = (MyApp) getApplication();
		view.setText(String.valueOf(application.getNumber()));
	}

	/*
	 * Prepare Intent
	 */

	public static Intent createQuery(Context context, String query, String value) {
		Intent i = new Intent(context, MyListActivity.class);
		i.putExtra("QUERY", query);
		i.putExtra("VALUE", value);
		return i;
	}

}
