package com.vogella.android.countries;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/** This activity shows a list of countries registered in the system. */
public class MainActivity extends ListActivity {

	/** Adapter showing a list of strings */
	 static final class StringListAdapter extends ArrayAdapter<String> {
		public StringListAdapter(Context context, List<String> items) {
			super(context, android.R.layout.simple_list_item_1, android.R.id.text1, items);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// get list of countries
		List<String> countries = CountryUtils.getCountries();
		
		// create and assign list adapter
		setListAdapter(new StringListAdapter(this, countries));
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, "hswlsd", Toast.LENGTH_LONG).show();
		Toast.makeText(this, "Selected: " + item, Toast.LENGTH_SHORT);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// create options menu
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
