package com.vogella.android.rssreader.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

public class RssFeedProvider {
	
	private static final boolean DEBUG = false;
	
	static final String PUB_DATE = "pubDate";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LINK = "link";
	static final String TITLE = "title";
	static final String ITEM = "item";

	public static List<RssItem> parse(String rssFeed) {
		List<RssItem> list = new ArrayList<RssItem>();
		XmlPullParser parser = Xml.newPullParser();
		InputStream stream = null;
		try {
			// auto-detect the encoding from the stream
			stream = new URL(rssFeed).openConnection().getInputStream();
			parser.setInput(stream, null);
			int eventType = parser.getEventType();
			boolean done = false;
			RssItem item = null;
			while (eventType != XmlPullParser.END_DOCUMENT && !done) {
				String name = null;
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if (name.equalsIgnoreCase(ITEM)) {
						if (DEBUG) Log.i("new item", "Create new item");
						item = new RssItem();
					} else if (item != null) {
						if (name.equalsIgnoreCase(LINK)) {
							if (DEBUG) Log.i("Attribute", "setLink");
							item.setLink(parser.nextText());
						} else if (name.equalsIgnoreCase(DESCRIPTION)) {
							if (DEBUG) Log.i("Attribute", "description");
							item.setDescription(parser.nextText().trim());
						} else if (name.equalsIgnoreCase(PUB_DATE)) {
							if (DEBUG) Log.i("Attribute", "date");
							item.setPubDate(parser.nextText());
						} else if (name.equalsIgnoreCase(TITLE)) {
							if (DEBUG) Log.i("Attribute", "title");
							item.setTitle(parser.nextText().trim());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					name = parser.getName();
					if (DEBUG) Log.i("End tag", name);
					if (name.equalsIgnoreCase(ITEM) && item != null) {
						if (DEBUG) Log.i("Added", item.toString());
						list.add(item);
					} else if (name.equalsIgnoreCase(CHANNEL)) {
						done = true;
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
