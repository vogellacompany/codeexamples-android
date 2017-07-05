package com.example.android.rssreader;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.List;

import static com.example.android.rssreader.RssApplication.RSS_UPDATE;

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
        RssApplication.bus.post(RSS_UPDATE);
    }
}