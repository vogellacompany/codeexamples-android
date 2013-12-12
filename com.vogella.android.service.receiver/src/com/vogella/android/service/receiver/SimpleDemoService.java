package com.vogella.android.service.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SimpleDemoService extends Service  {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
