package com.vogella.android.debugging.listview;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.os.Debug;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyArrayAdapter extends ArrayAdapter<String> {

	private List<String> values;
	private Context context;

	public MyArrayAdapter(Context context, List<String> values) {
		super(context, android.R.layout.simple_list_item_1);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Ensure sorted values
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(values.get(position));
		return view;
	}
}
