package de.vogella.android.ownservice.aidl;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class WordService extends Service {
	private List<MyMessage> list = new ArrayList<MyMessage>();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("AIDL", "Service started");
		list.add(new MyMessage("Tennesee", 10));
		list.add(new MyMessage("Bayern", 20));
		list.add(new MyMessage("Schleswig-Holstein", 30));
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new IWordService.Stub() {
			@Override
			public List<MyMessage> getWords() throws RemoteException {
				return list;
			}
		};
	}
}
