package de.vogella.android.listview.asynchronous.nocache;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new MyImageAdapter());
	}

}