package de.vogella.android.autocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteDemo extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		AutoCompleteTextView view = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		String[] values = new String[] { "Linux", "Ubuntu", "iPhone", "Android" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, values);
		view.setAdapter(adapter);
	}
}