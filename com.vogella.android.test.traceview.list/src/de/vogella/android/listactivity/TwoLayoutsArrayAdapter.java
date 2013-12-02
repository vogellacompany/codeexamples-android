package de.vogella.android.listactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TwoLayoutsArrayAdapter extends ArrayAdapter<String> {
	private final String[] values;
	private LayoutInflater inflator;

	static class ViewHolder {
		public TextView text;
		public ImageView image;
	}

	public TwoLayoutsArrayAdapter(Context context, String[] values) {
		super(context, R.id.TextView01, values);
		this.values = values;
		inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return (position % 2 == 0) ? 0 : 1;
	}

	// public int getItemViewTypeInternal(int position) {
	// return (position % 2 == 0) ? 0 : 1;
	// }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = null;
		if (getItemViewType(position) == 0) {
			rowView = inflator.inflate(R.layout.row_even, null);
		} else {
			rowView = inflator.inflate(R.layout.row_odd, null);
		}
		TextView text = (TextView) rowView.findViewById(R.id.label);
		text.setText(values[position]);
		return rowView;
	}
}
