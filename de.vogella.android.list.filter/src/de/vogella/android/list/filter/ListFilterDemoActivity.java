package de.vogella.android.list.filter;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class ListFilterDemoActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				getModel());
		setListAdapter(adapter);
		EditText filterEditText = (EditText) findViewById(R.id.filterText);
		filterEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.getFilter().filter(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private List<String> getModel() {
		List<String> list = new ArrayList<String>();
		list.add("Linux");
		list.add("Windows7");
		list.add("Suse");
		list.add("Eclipse");
		list.add("Ubuntu");
		list.add("Solaris");
		list.add("Android");
		list.add("iPhone");
		return list;
	}
}