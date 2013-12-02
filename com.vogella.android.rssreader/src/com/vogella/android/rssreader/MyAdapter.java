package com.vogella.android.rssreader;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vogella.android.rssreader.utils.RssItem;

public class MyAdapter extends ArrayAdapter<RssItem> {

	private Context context;
	private List<RssItem> items;

	public MyAdapter(Context context, int textViewResourceId,
			List<RssItem> items) {
		super(context, textViewResourceId, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(items.get(position).getTitle());
		return view;
	}
}
