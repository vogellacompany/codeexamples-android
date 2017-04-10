package de.vogella.android.todos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import de.vogella.android.todos.contentprovider.MyTodoContentProvider;
import de.vogella.android.todos.database.TodoTable;

/*
 * TodoDetailActivity allows to enter a new todo item 
 * or to change an existing
 */
public class TodoDetailActivity extends Activity {
	private Spinner mCategory;
	private EditText mTitleText; 
	private EditText mBodyText;

	private Uri todoUri;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.todo_edit);

		mCategory = (Spinner) findViewById(R.id.category);
		mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
		mBodyText = (EditText) findViewById(R.id.todo_edit_description);
		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);

		Bundle extras = getIntent().getExtras();

		// Check from the saved Instance
		todoUri = (bundle == null) ? null : (Uri) bundle
				.getParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE);

		// Or passed from the other activity
		if (extras != null) {
			todoUri = extras
					.getParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE);

			fillData(todoUri);
		}

		confirmButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (TextUtils.isEmpty(mTitleText.getText().toString())) {
					makeToast();
				} else {
					setResult(RESULT_OK);
					finish();
				}
			}

		});
		
	}

	private void fillData(Uri uri) {
		String[] projection = { TodoTable.COLUMN_SUMMARY,
				TodoTable.COLUMN_DESCRIPTION, TodoTable.COLUMN_CATEGORY };
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			String category = cursor.getString(cursor
					.getColumnIndexOrThrow(TodoTable.COLUMN_CATEGORY));

			for (int i = 0; i < mCategory.getCount(); i++) {

				String s = (String) mCategory.getItemAtPosition(i);
				if (s.equalsIgnoreCase(category)) {
					mCategory.setSelection(i);
				}
			}

			mTitleText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(TodoTable.COLUMN_SUMMARY)));
			mBodyText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(TodoTable.COLUMN_DESCRIPTION)));

			// Always close the cursor
			cursor.close();
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE, todoUri);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	private void saveState() {
		String category = (String) mCategory.getSelectedItem();
		String summary = mTitleText.getText().toString();
		String description = mBodyText.getText().toString();

		// Only save if either summary or description
		// is available

		if (description.length() == 0 && summary.length() == 0) {
			return;
		}

		ContentValues values = new ContentValues();
		values.put(TodoTable.COLUMN_CATEGORY, category);
		values.put(TodoTable.COLUMN_SUMMARY, summary);
		values.put(TodoTable.COLUMN_DESCRIPTION, description);

		if (todoUri == null) {
			// New todo
			todoUri = getContentResolver().insert(
					MyTodoContentProvider.CONTENT_URI, values);
		} else {
			// Update todo
			getContentResolver().update(todoUri, values, null, null);
		}
	}

	private void makeToast() {
		Toast.makeText(TodoDetailActivity.this, "Please maintain a summary",
				Toast.LENGTH_LONG).show();
	}
}
