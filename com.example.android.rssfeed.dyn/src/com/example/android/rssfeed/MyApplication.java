package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.example.android.rssfeedlibrary.RssItem;

public class MyApplication extends Application {
	public static List<RssItem> list = new ArrayList<RssItem>();
}
