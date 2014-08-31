package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.example.android.rssfeedlibrary.RssItem;

public class RssApplication extends Application {
	public static List<RssItem> list = new ArrayList<RssItem>();
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}
	
}
