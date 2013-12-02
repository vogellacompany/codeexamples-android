package com.vogella.android.countries;

import android.app.Application;

public class CountriesApp extends Application {

	private DatabaseHelper mDatabaseHelper;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mDatabaseHelper = new DatabaseHelper(this);
	}
	
	public DatabaseHelper getDatabase() {
		return mDatabaseHelper;
	}
	
}
