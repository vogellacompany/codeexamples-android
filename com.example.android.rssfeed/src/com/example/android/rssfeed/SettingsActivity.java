package com.example.android.rssfeed;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class SettingsActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.myprefs);
		
		// show the current value in the settings screen
		for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
			initSummary(getPreferenceScreen().getPreference(i));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	 }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		updatePreferences(findPreference(key));
	}

	private void initSummary(Preference p) {
		if (p instanceof PreferenceCategory) {
			PreferenceCategory cat = (PreferenceCategory) p;
			for (int i = 0; i < cat.getPreferenceCount(); i++) {
				initSummary(cat.getPreference(i));
			}
		} else {
			updatePreferences(p);
		}
	}

	private void updatePreferences(Preference p) {
		if (p instanceof EditTextPreference) {
			EditTextPreference editTextPref = (EditTextPreference) p;
			p.setSummary(editTextPref.getText());
		}
	}
}
