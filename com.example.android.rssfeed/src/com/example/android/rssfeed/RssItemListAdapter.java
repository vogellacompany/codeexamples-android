package com.example.android.rssfeed;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.rssfeedlibrary.RssItem;

public class RssItemListAdapter extends ArrayAdapter<RssItem> {

	private Context context;
	private List<RssItem> items;

	public RssItemListAdapter(Context context, List<RssItem> items) {
		super(context, android.R.layout.simple_list_item_1, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// not performance optimized
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.listview_rowlayout, null);
		TextView rssTitelView = (TextView) view.findViewById(R.id.rsstitle);
		rssTitelView.setText(items.get(position).getTitle());
		TextView rssUrlView = (TextView) view.findViewById(R.id.rssurl);
		rssUrlView.setText(items.get(position).getLink());
		return view;
	}
}
