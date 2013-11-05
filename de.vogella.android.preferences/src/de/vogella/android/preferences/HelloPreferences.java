package de.vogella.android.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HelloPreferences extends Activity {
	private static final int MENU_SECOND = 1;
	private static final int MENU_FIRST = 0;
	SharedPreferences preferences;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		EditText text = (EditText) findViewById(R.id.contextmenu);
		registerForContextMenu(text);
		Button button = (Button) findViewById(R.id.Button01);
		// Initialize preferences
		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String username = preferences.getString("username", "n/a");
				String password = preferences.getString("password", "n/a");
				Toast.makeText(
						HelloPreferences.this,
						"You entered user: " + username + " and password: "
								+ password, Toast.LENGTH_LONG).show();

			}
		});

		Button buttonChangePreferences = (Button) findViewById(R.id.Button02);
		buttonChangePreferences.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Editor edit = preferences.edit();
				String username = preferences.getString("username", "n/a");
				// We will just revert the current user name and save again
				StringBuffer buffer = new StringBuffer();
				for (int i = username.length() - 1; i >= 0; i--) {
					buffer.append(username.charAt(i));
				}
				edit.putString("username", buffer.toString());
				edit.commit();
				// A toast is a view containing a quick little message for the
				// user. We give a little feedback
				Toast.makeText(HelloPreferences.this,
						"Reverted string sequence of user name.",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// We have only one menu option
		case R.id.preferences:
			// Launch Preference activity
			Intent i = new Intent(HelloPreferences.this, Preferences.class);
			startActivity(i);
			// Some feedback to the user
			Toast.makeText(HelloPreferences.this,
					"Here you can enter your user credentials.",
					Toast.LENGTH_LONG).show();
			break;

		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// We could make a distinction if several views would have registered a
		// context menu. We could also use a XML file but this time we do it
		// manually
		menu.add(Menu.NONE, MENU_FIRST, Menu.NONE, "Say hello");
		menu.add(Menu.NONE, MENU_SECOND, Menu.NONE, "Another option");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_FIRST:
			Toast.makeText(this, "First option selected", Toast.LENGTH_SHORT)
					.show();
			break;
		case MENU_SECOND:
			Toast.makeText(this, "Second option selected", Toast.LENGTH_SHORT)
					.show();
			break;

		}
		return super.onContextItemSelected(item);
	}
}