package de.vogella.android.listactivity.adapters;

import de.vogella.android.listactivity.R;
import de.vogella.android.listactivity.R.drawable;
import de.vogella.android.listactivity.R.id;
import de.vogella.android.listactivity.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MySimpleArrayAdapter(Context context, String[] values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
		String s = values[position];
		textView.setText(s);
		
		// Change the icon for iPhone
		if (s.startsWith("iPhone")) {
			imageView.setImageResource(R.drawable.no);
		} else {
			imageView.setImageResource(R.drawable.ok);
		}
		return rowView;
	}
}
