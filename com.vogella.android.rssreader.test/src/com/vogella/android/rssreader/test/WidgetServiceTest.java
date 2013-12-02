package com.vogella.android.rssreader.test;

import android.content.Intent;
import android.test.ServiceTestCase;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.vogella.android.rssreader.widget.RssfeedWidgetService;

public class WidgetServiceTest extends ServiceTestCase<RssfeedWidgetService> {

	public WidgetServiceTest() {
		super(RssfeedWidgetService.class);
	}
	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	public void testStartService() throws Exception {
		Intent i = new Intent(getContext(), RssfeedWidgetService.class);
		startService(i);
		RssfeedWidgetService service = getService();
		RemoteViewsFactory onGetViewFactory = service.onGetViewFactory(i);
		assertNotNull(onGetViewFactory);
	}
}
