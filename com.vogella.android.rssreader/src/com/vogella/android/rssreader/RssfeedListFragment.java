package com.vogella.android.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vogella.android.rssreader.utils.RssFeedProvider;
import com.vogella.android.rssreader.utils.RssItem;

public class RssfeedListFragment extends ListFragment {

	public static interface OnItemSelectedListener {
		public void onRssItemSelected(String link);
	}

	private static class ParseTask extends AsyncTask<String, Void, List<RssItem>> {
		private RssfeedListFragment fragment;

		public void setFragment(RssfeedListFragment fragment) {
			this.fragment = fragment;
		}

		@Override
		protected List<RssItem> doInBackground(String... params) {
			List<RssItem> list = RssFeedProvider.parse(params[0]);
			return list;
		}

		@Override
		protected void onPostExecute(List<RssItem> result) {
			fragment.setListContent(result);
		}
	}
	
	private OnItemSelectedListener listener;
	private ParseTask parseTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		MyAdapter adapter = new MyAdapter(getActivity(), 
				android.R.layout.simple_list_item_1,
				new ArrayList<RssItem>());
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		RssItem item = (RssItem) getListAdapter().getItem(position);
		updateDetail(item);
	}

	public void updateListContent() {
		if (parseTask == null) {
			parseTask = new ParseTask();
			parseTask.setFragment(this);
			parseTask.execute("http://www.vogella.com/article.rss");
		}
	}

	@SuppressWarnings("unchecked")
	public void setListContent(List<RssItem> result) {
		ArrayAdapter<RssItem> listAdapter = (ArrayAdapter<RssItem>) getListAdapter();
		listAdapter.clear();
		listAdapter.addAll(result);
		parseTask.setFragment(null);
		parseTask = null;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			listener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString() + " must implemenet MyListFragment.OnItemSelectedListener");
		}
	}

	public void updateDetail(RssItem item) {
		listener.onRssItemSelected(item.getLink());
	}
}