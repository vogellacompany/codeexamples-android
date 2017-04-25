package com.vogella.android.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class LocalWordService extends Service {
    private final IBinder mBinder = new MyBinder();
	private List<String> resultList = new ArrayList<String>();
	private int counter = 1;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		addResultValues();
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		addResultValues();
		return mBinder;
	}

	public class MyBinder extends Binder {
		LocalWordService getService() {
			return LocalWordService.this;
		}
	}

	public List<String> getWordList() {
		return resultList;
	}

	private void addResultValues() {
		Random random = new Random();
		List<String> input = Arrays.asList("Linux", "Android","iPhone","Windows7" );
		resultList.add(input.get(random.nextInt(3)) + " " + counter++);
		if (counter == Integer.MAX_VALUE) {
			counter = 0;
		}
	}
}
