package de.vogella.android.listactivity.cursor;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class MyListActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Cursor mCursor = getContacts();
		startManagingCursor(mCursor);
		// Now create a new list adapter bound to the cursor.
		// SimpleListAdapter is designed for binding to a Cursor.
		ListAdapter adapter = new SimpleCursorAdapter(this, // Context.
				android.R.layout.simple_list_item_2, // Specify the row template
														// to use (here, two
														// columns bound to the
														// two retrieved cursor
														// rows).
				mCursor, // Pass in the cursor to bind to.
				// Array of cursor columns to bind to.
				new String[] { ContactsContract.Contacts._ID,
						ContactsContract.Contacts.DISPLAY_NAME },
				// Array of layout variables to bind to
				new int[] { android.R.id.text1, android.R.id.text2 });

		// Bind to our new adapter.
		setListAdapter(adapter);
	}

	private Cursor getContacts() {
		// Run query
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };
		String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
				+ ("1") + "'";
		String[] selectionArgs = null;
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
				+ " COLLATE LOCALIZED ASC";

		return managedQuery(uri, projection, selection, selectionArgs,
				sortOrder);
	}

}