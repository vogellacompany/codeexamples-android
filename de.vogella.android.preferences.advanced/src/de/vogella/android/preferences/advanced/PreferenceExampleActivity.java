package de.vogella.android.preferences.advanced;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceExampleActivity extends PreferenceActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mypreferences);

	}
}