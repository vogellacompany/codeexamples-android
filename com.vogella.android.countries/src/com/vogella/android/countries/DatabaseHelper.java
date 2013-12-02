package com.vogella.android.countries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final boolean DEBUG = false;
	private static final String TAG = "dbhelper";
	
	protected final Context context;
	
	public DatabaseHelper(Context context) {
		super(context, "countries.db", null, 1);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		if (DEBUG) Log.d(TAG, "creating db ...");
		long started = System.currentTimeMillis();
		executeAssetScript("create.sql", db);
		if (DEBUG) Log.d(TAG, "db created in " + (System.currentTimeMillis() - started) + " ms");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// not yet implemented
	}
	
	/**
	 * Execute an SQL script located within "asset" folder line by line
	 * 
	 * @param fileName
	 * @param db
	 */
	public void executeAssetScript(String fileName, SQLiteDatabase db) {
		
		if (DEBUG) Log.d(TAG, "executing script: " + fileName);
		try {
			InputStream is = context.getAssets().open(fileName);
			try {
				StringBuilder statement = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = reader.readLine();
				while (line != null) {
					line = line.trim();
					if (line.length() > 0 && !line.startsWith("--")) {
						statement.append(' ').append(line);
						if (line.endsWith(";;")) {
							// remove last ";"
							statement.setLength(statement.length() - 1);
							// create SQL query
							String sql = statement.toString().trim();
							// execute SQL query
							db.execSQL(sql);
							if (DEBUG) Log.d(TAG, sql);
							// reset statement
							statement.setLength(0);
						}
					}
					line = reader.readLine();
				}
			} finally {
				if (is != null) is.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
    public void beginTransaction() {
    	getWritableDatabase().beginTransaction();
    }

    public void setTransactionSuccessfull() {
    	getWritableDatabase().setTransactionSuccessful();
    }
    
    public void endTransaction() {
    	getWritableDatabase().endTransaction();
    }
    
}
