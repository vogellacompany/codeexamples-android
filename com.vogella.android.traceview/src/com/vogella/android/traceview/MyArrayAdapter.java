package com.vogella.android.traceview;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
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
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
		Collections.sort(values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Ensure sorted values
		View view = convertView;
		if (view == null) {

		}
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.rowlayout, parent, false);
		Resources res = context.getResources();
		String text = String.format(res.getString(R.string.number_template),
				values.get(position));
		CharSequence styledText = Html.fromHtml(text);
		TextView textView = (TextView) view.findViewById(R.id.textView3);
		textView.setText(styledText);
		return view;
	}
}
