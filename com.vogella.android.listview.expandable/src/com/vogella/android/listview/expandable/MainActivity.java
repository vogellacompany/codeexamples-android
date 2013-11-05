package com.vogella.android.listview.expandable;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// More efficient than HashMap for mapping integers to objects
	SparseArray<Group> groups = new SparseArray<Group>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createData();
		ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
		MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
				groups);
		listView.setAdapter(adapter);
	}

	public void createData() {
		for (int j = 0; j < 5; j++) {
			Group group = new Group("Test " + j);
			for (int i = 0; i < 5; i++) {
				group.children.add("Sub Item" + i);
			}
			groups.append(j, group);
		}
	}

}
