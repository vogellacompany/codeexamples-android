package com.example.android.rssfeed;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssItem;

public class RssDownloadService extends IntentService {
	
	public static String NOTIFICATION = "rssfeedupdated";
	public RssDownloadService() {
		super("RssDownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		String string = extras.getString("url");
		List<RssItem> list = RssFeedProvider.parse(string);
		RssApplication.list = list;
		Intent i = new Intent(NOTIFICATION);
		sendBroadcast(i);		
	}
}
