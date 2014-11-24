package com.vogella.android.recyclerview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {
	private RecyclerView recyclerView;
	private MyAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		// recyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(mLayoutManager);
		// Use the default animator
		// recyclerView.setItemAnimator(new DefaultItemAnimator());
		// you could add item decorators
		//	RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
		//	recyclerView.addItemDecoration(itemDecoration);

		ArrayList<String> input = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			input.add("Test" + i);
		}

		// specify an adapter (see also next example)
		mAdapter = new MyAdapter(input);
		recyclerView.setAdapter(mAdapter);
		mAdapter.remove("Test1");
	}
}