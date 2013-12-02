package com.vogella.android.rssreader.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.vogella.android.rssreader.R;
import com.vogella.android.rssreader.utils.RssItem;

public class RssfeedWidgetService extends RemoteViewsService {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new RssfeedWidgetViewsFactory(getApplicationContext(), intent);
	}

}

class RssfeedWidgetViewsFactory implements RemoteViewsFactory {

	private final Context mContext;

	public RssfeedWidgetViewsFactory(Context context, Intent intent) {
		mContext = context;
	}

	@Override
	public void onCreate() {
		// nop
	}

	@Override
	public void onDataSetChanged() {
		// nop, but normally we query data here
	}

	@Override
	public void onDestroy() {
		// nop, but normally we close our cursor here
	}

	@Override
	public int getCount() {
		return InMemoryDataStorage.get().getCount();
	}

	@Override
	public RemoteViews getViewAt(int position) {

		RssItem feed = InMemoryDataStorage.get().getItem(position);
		RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
		views.setTextViewText(R.id.item, feed.getTitle());
		
		Intent fillInIntent = new Intent();
		Bundle extras = new Bundle();
		extras.putString(RssfeedWidgetProvider.EXT_LINK, feed.getLink());
		fillInIntent.putExtras(extras);
		views.setOnClickFillInIntent(R.id.item, fillInIntent);
		
		return views;
	}

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}
