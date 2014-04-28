package com.example.android.rssfeed;

import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.rssfeedlibrary.RssItem;

public class MyListFragment extends ListFragment {
	private OnItemSelectedListener listener;

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			setListContent(RssApplication.list);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<RssItem> list = RssApplication.list;
		RssItemListAdapter adapter = new RssItemListAdapter(getActivity(), list);
		setListAdapter(adapter);
		// setRetainInstance(true);
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getActivity().registerReceiver(receiver,
				new IntentFilter(RssDownloadService.NOTIFICATION));
	}

	@Override
	public void onStop() {
		super.onStop();
		getActivity().unregisterReceiver(receiver);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		RssItem item = (RssItem) getListAdapter().getItem(position);
		updateDetail(item);
	}

	public void setListContent(List<RssItem> result) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<RssItem> listAdapter = (ArrayAdapter<RssItem>) getListAdapter();
		listAdapter.clear();
		listAdapter.addAll(result);
		
	}

	public interface OnItemSelectedListener {
		public void onRssItemSelected(String link);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			listener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	public void updateDetail(RssItem item) {
		listener.onRssItemSelected(item.getLink());
	}
}