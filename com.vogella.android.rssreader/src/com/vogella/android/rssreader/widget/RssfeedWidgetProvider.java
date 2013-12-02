package com.vogella.android.rssreader.widget;

import java.util.Date;
import java.util.List;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

import com.vogella.android.rssreader.R;
import com.vogella.android.rssreader.RssfeedActivity;
import com.vogella.android.rssreader.utils.RssFeedProvider;
import com.vogella.android.rssreader.utils.RssItem;

public class RssfeedWidgetProvider extends AppWidgetProvider {

	public static final String ACTION_REFRESH = "com.vogella.rssfeed.widget.REFRESH";
	public static final String ACTION_SHOW_LINK = "com.vogella.rssfeed.widget.SHOW";
	public static final String EXT_LINK = "link";
	
	@Override
	public void onEnabled(Context context) {
		// first widget was added, nop for now
		super.onEnabled(context);
	}

	@Override
	public void onReceive(final Context context, Intent intent) {
		// user pressed an item or a refresh button
		
		String action = intent.getAction();
		
		if (ACTION_REFRESH.equals(action)) { // refresh data 
			WorkerThread.get().post(new Runnable() {
				@Override public void run() {

					// get data and store it locally
					List<RssItem> feeds = RssFeedProvider.parse("http://www.vogella.com/article.rss");
					InMemoryDataStorage.get().setData(feeds);
					
					// request update for all widgets
                    AppWidgetManager mgr = AppWidgetManager.getInstance(context);
                    int[] widgetIds = mgr.getAppWidgetIds(new ComponentName(context, RssfeedWidgetProvider.class));
                    mgr.notifyAppWidgetViewDataChanged(widgetIds, R.id.feeds_list);

				}
			});
			
		} else if (ACTION_SHOW_LINK.equals(action)) { // show the feed
            String link = intent.getStringExtra(EXT_LINK);
            
            Intent launchIntent = new Intent(context, RssfeedActivity.class);
            launchIntent.putExtra(RssfeedActivity.EXTRA_URL, link);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launchIntent);
            
		}
		
		super.onReceive(context, intent);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; ++i) {
            RemoteViews layout = createWidgetLayout(context, appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds[i], layout);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
 	}

	private static RemoteViews createWidgetLayout(Context context, int appWidgetId) {
		
		// create layout
		Intent intent = new Intent(context, RssfeedWidgetService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.setRemoteAdapter(R.id.feeds_list, intent);
        views.setEmptyView(R.id.feeds_list, R.layout.widget_empty_layout);

        // set last update time
        String time = DateFormat.getTimeFormat(context).format(new Date());
        String date = DateFormat.getDateFormat(context).format(new Date());
        views.setTextViewText(R.id.last_update, String.format("%1$s, %2$s", time, date));
        
        // assign onRefresh handler
        Intent onRefreshIntent = new Intent(context, RssfeedWidgetProvider.class);
        onRefreshIntent.setAction(ACTION_REFRESH);
        PendingIntent onRefreshPending = PendingIntent.getBroadcast(
        		context, 0, onRefreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.refresh, onRefreshPending);
		
        // assign onItemClick handler
        Intent onItemClickIntent = new Intent(context, RssfeedWidgetProvider.class);
        onItemClickIntent.setAction(ACTION_SHOW_LINK);
        onItemClickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        onItemClickIntent.setData(Uri.parse(onItemClickIntent.toUri(Intent.URI_INTENT_SCHEME)));
        final PendingIntent onItemClickPending = PendingIntent.getBroadcast(context, 0,
        		onItemClickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.feeds_list, onItemClickPending);
        
		return views;
	}
	
	
}


class WorkerThread {
	
	private static WorkerThread sInstance;

	public static WorkerThread get() {
		if (sInstance == null) {
			sInstance = new WorkerThread();
		}
		return sInstance;
	}
	
	private HandlerThread mThread;
	private Handler mQueue;

	public WorkerThread() {
		mThread = new HandlerThread("RssfeedWidgetProvider-worker");
		mThread.start();
		mQueue = new Handler(mThread.getLooper());
	}
	
	public void post(Runnable task) {
		mQueue.removeMessages(0);
		mQueue.post(task);
	}
}

/**
 * In-memory storage for read data
 * @author sergej
 */
class InMemoryDataStorage {
	
	private static final InMemoryDataStorage sInstance = new InMemoryDataStorage();
	
	public static InMemoryDataStorage get() {
		return sInstance;
	}
	
	private List<RssItem> mFeeds;
	
	public void setData(List<RssItem> feeds) {
		mFeeds = feeds;
	}
	
	public List<RssItem> getData() {
		return mFeeds;
	}

	public int getCount() {
		return mFeeds == null ? 0 : mFeeds.size();
	}

	public RssItem getItem(int position) {
		return mFeeds.get(position);
	}
	
}