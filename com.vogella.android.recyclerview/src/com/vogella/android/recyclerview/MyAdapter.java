package com.vogella.android.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
		implements View.OnClickListener {
	private ArrayList<String> mDataset;

	// Provide a reference to the views for each data item
	public class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView bigTextView;
		public TextView smallTextView;

		public ViewHolder(TextView big, TextView small) {
			super(big);
			bigTextView = big;
			smallTextView = small;
		}
	}

	public void add(int position, String item) {
		mDataset.add(position, item);
		notifyItemInserted(position);
	}

	public void remove(String item) {
		int position = mDataset.indexOf(item);
		mDataset.remove(position);
		notifyItemRemoved(position);
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public MyAdapter(ArrayList<String> myDataset) {
		mDataset = myDataset;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
		TextView  bigText = (TextView) v.findViewById(R.id.firstLine);
		TextView  smallText = (TextView) v.findViewById(R.id.secondLine);
		ViewHolder vh = new ViewHolder(bigText, smallText);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		final String name = mDataset.get(position);
		holder.bigTextView.setText(mDataset.get(position));
		holder.bigTextView.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	remove(name);
		        }
		    });
		holder.smallTextView.setText("Example data");
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.size();
	}

	@Override
	public void onClick(View v) {

	}
}