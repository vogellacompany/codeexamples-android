package de.vogella.android.listactivity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MyListActivity extends ListActivity {
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// setContentView(R.layout.main);
		// List<Person> values = createModel();
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
				"OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS",
				"Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu",
				"Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7",
				"Max OS X", "Linux", "OS/2", "Android", "iPhone",
				"WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
				"Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
				"OS/2", "Android", "iPhone", "WindowsMobile", "Blackberry",
				"WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
				"Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu",
				"Windows7", "Max OS X", "Linux", "OS/2" };
		InteractiveArrayAdapter adapter = new InteractiveArrayAdapter(this,
				getModel());
		// TwoLayoutsArrayAdapter adapter = new TwoLayoutsArrayAdapter(this,
		// values);
		setListAdapter(adapter);
	}

	private List<Person> createModel() {
		ArrayList<Person> list = new ArrayList<Person>();
		Person person = new Person("Peter", "Parker");
		list.add(person);
		person = new Person("Jim", "Parker");
		list.add(person);
		person = new Person("Kim", "Parker");
		list.add(person);
		person = new Person("Bim", "Parker");
		list.add(person);
		return list;
	}

	private Model get(String s) {
		return new Model(s);
	}

	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		list.add(get("Linux"));
		list.add(get("Windows7"));
		list.add(get("Suse"));
		list.add(get("Eclipse"));
		list.add(get("Ubuntu"));
		list.add(get("Solaris"));
		list.add(get("Android"));
		list.add(get("iPhone"));
		list.add(get("Linux"));
		list.add(get("Windows7"));
		list.add(get("Suse"));
		list.add(get("Eclipse"));
		list.add(get("Ubuntu"));
		list.add(get("Solaris"));
		list.add(get("Android"));
		list.add(get("iPhone"));
		list.add(get("Linux"));
		list.add(get("Windows7"));
		list.add(get("Suse"));
		list.add(get("Eclipse"));
		list.add(get("Ubuntu"));
		list.add(get("Solaris"));
		list.add(get("Android"));
		list.add(get("iPhone"));
		list.add(get("Linux"));
		list.add(get("Windows7"));
		list.add(get("Suse"));
		list.add(get("Eclipse"));
		list.add(get("Ubuntu"));
		list.add(get("Solaris"));
		list.add(get("Android"));
		list.add(get("iPhone"));
		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		// SAVE DATA to database
	}
}