package de.vogella.android.ownservice.aidl;

import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	private ArrayAdapter<MyMessage> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<MyMessage>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		bindService(new Intent(this, WordService.class), conn,
				Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection conn = new ServiceConnection() {

		private IWordService wordService;

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("AIDL", "Service Connected");
			wordService = IWordService.Stub.asInterface(service);
			try {
				List<MyMessage> words = wordService.getWords();
				adapter.clear();
				adapter.addAll(words);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			wordService = null;
		}
	};

}