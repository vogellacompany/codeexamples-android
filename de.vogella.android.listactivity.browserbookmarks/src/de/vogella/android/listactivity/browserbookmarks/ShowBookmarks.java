package de.vogella.android.listactivity.browserbookmarks;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShowBookmarks extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// We select the id, the URL and the title from the database
		// the ID is handy for the reaction on the selection
		String[] selection = { Browser.BookmarkColumns._ID,
				Browser.BookmarkColumns.URL, Browser.BookmarkColumns.TITLE };
		String[] displayFields = { Browser.BookmarkColumns.URL,
				Browser.BookmarkColumns.TITLE };
		int[] viewFields = { android.R.id.text1, android.R.id.text2 };
		Cursor cursor = managedQuery(Browser.BOOKMARKS_URI, selection, null,
				null, null);
		startManagingCursor(cursor);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor, displayFields,
				viewFields);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TextView view = (TextView) v.findViewById(android.R.id.text2);
		Toast.makeText(this, view.getText(), Toast.LENGTH_SHORT).show();
	}
}