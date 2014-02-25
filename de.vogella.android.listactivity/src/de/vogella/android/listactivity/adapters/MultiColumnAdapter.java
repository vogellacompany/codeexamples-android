package de.vogella.android.listactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MultiColumnAdapter extends ArrayAdapter<String> {
	private final String[] values;
	private LayoutInflater inflator;

	public MultiColumnAdapter(Context context, String[] values) {
		super(context, 0, values);
		this.values = values;
		inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout listLayout = new LinearLayout(getContext());
		listLayout.setLayoutParams(new AbsListView.LayoutParams(
				AbsListView.LayoutParams.WRAP_CONTENT,
				AbsListView.LayoutParams.WRAP_CONTENT));
		listLayout.setId(5000);
		for (int i = 0; i <= 5; i++) {
			TextView v = new TextView(getContext());
			v.setText(String.valueOf(i));
			listLayout.addView(v);
		}
		return listLayout;
	}
}
