package com.vogella.android.rssreader;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.vogella.android.rssreader.utils.RssItem;

public class MyApplication extends Application {
	public static List<RssItem> list = new ArrayList<RssItem>();
}
