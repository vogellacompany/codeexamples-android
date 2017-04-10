package de.vogella.android.todos.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoTable {

	// Database table
	public static final String TABLE_TODO = "todo";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_SUMMARY = "summary";
	public static final String COLUMN_DESCRIPTION = "description";

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table " 
			+ TABLE_TODO
			+ "(" + COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_CATEGORY + " text not null, " 
			+ COLUMN_SUMMARY + " text not null," 
			+ COLUMN_DESCRIPTION
			+ " text not null" + ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(TodoTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
		onCreate(database);
	}
}
