package com.vogella.android.databackup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

public class MyBackupAgent extends BackupAgentHelper {
	
	// The name of the SharedPreferences file
	static final String PREFS = "myprefs";

	// A key to uniquely identify the set of backup data
	static final String PREFS_BACKUP_KEY = "myprefs";

	@Override
	public void onCreate() {
		SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(
				this, PREFS);
		addHelper(PREFS_BACKUP_KEY, helper);
	}

}
