package com.vogella.android.loader.preferences;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesLoader extends AsyncTaskLoader<SharedPreferences>
		implements SharedPreferences.OnSharedPreferenceChangeListener {
	private SharedPreferences prefs = null;

	public SharedPreferencesLoader(Context context) {
		super(context);
	}

	// Load the data asynchronously
	@Override
	public SharedPreferences loadInBackground() {
		prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		prefs.registerOnSharedPreferenceChangeListener(this);
		return (prefs);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// notify loader that content has changed
		onContentChanged();
	}

	/**
	 * starts the loading of the data 
	 * once result is ready the onLoadFinished method is called
	 * in the main thread. It loader was started earlier the result
	 * is return directly

	 * method must be called from main thread.
	 */
	@Override
	protected void onStartLoading() {
		if (prefs != null) {
			deliverResult(prefs);
		}

		if (takeContentChanged() || prefs == null) {
			forceLoad();
		}
	}
	
	public static void persist(final SharedPreferences.Editor editor) {
		editor.apply();
	}

}
