package com.vogella.android.loader.preferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements
		LoaderManager.LoaderCallbacks<SharedPreferences> {
	private static final String KEY = "prefs";
	private TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.prefs);
		getLoaderManager().initLoader(0, null, this);

	}

	@Override
	public Loader<SharedPreferences> onCreateLoader(int id, Bundle args) {
		return (new SharedPreferencesLoader(this));
	}

	@SuppressLint("CommitPrefEdits")
	@Override
	public void onLoadFinished(Loader<SharedPreferences> loader,
			SharedPreferences prefs) {
		int value = prefs.getInt(KEY, 0);
		value += 1;
		textView.setText(String.valueOf(value));
		// update value
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(KEY, value);
		SharedPreferencesLoader.persist(editor);
	}

	@Override
	public void onLoaderReset(Loader<SharedPreferences> loader) {
		// NOT used
	}
}