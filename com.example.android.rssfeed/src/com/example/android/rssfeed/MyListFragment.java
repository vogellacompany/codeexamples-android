package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssItem;

public class MyListFragment extends ListFragment {
	private OnItemSelectedListener listener;
	ParseTask parseTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<RssItem> list = new ArrayList<RssItem>();
		MyAdapter adapter = new MyAdapter(getActivity(),
				android.R.layout.simple_list_item_1, list);
		setListAdapter(adapter);
		setRetainInstance(true);
	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View view = inflater.inflate(R.layout.fragment_rsslist_overview,
	// container, false);
	//
	// return view;
	// }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		RssItem item = (RssItem) getListAdapter().getItem(position);
		updateDetail(item);
	}

	private static class ParseTask extends
			AsyncTask<String, Void, List<RssItem>> {
		private MyListFragment fragment;

		public void setFragment(MyListFragment fragment) {
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

	public void updateListContent() {
		if (parseTask == null) {
			parseTask = new ParseTask();
			parseTask.setFragment(this);
			parseTask.execute("http://www.vogella.com/article.rss");
		}
	}

	public void setListContent(List<RssItem> result) {
		ArrayAdapter listAdapter = (ArrayAdapter) getListAdapter();
		listAdapter.clear();
		listAdapter.addAll(result);
		parseTask.setFragment(null);
		parseTask = null;
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