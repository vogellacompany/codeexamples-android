package de.vogella.android.ownservice.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	Messenger messenger = null;

	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			Bundle data = message.getData();
			if (message.arg1 == RESULT_OK && data != null) {
				String text = data
						.getString(DownloadServiceMessenger.RESULTPATH);
				Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG)
						.show();
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	private ServiceConnection conn = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder binder) {
			messenger = new Messenger(binder);

		}

		public void onServiceDisconnected(ComponentName className) {
			messenger = null;
		}
	};

	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "OnResume called", Toast.LENGTH_SHORT).show();
		Intent intent = null;
		intent = new Intent(this, DownloadServiceMessenger.class);
		// Create a new Messenger for the communication back
		// From the Service to the Activity
		Messenger messenger = new Messenger(handler);
		intent.putExtra("MESSENGER", messenger);

		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unbindService(conn);
	}

	public void onClick(View view) {
		Message msg = Message.obtain();

		try {
			Bundle bundle = new Bundle();
			bundle.putString(DownloadServiceMessenger.FILENAME, "index.html");
			bundle.putString(DownloadServiceMessenger.URLPATH,
					"http://www.vogella.de/index.html");
			msg.setData(bundle);
			messenger.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}