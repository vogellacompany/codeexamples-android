package de.vogella.android.handler.looper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
	// We use Handler to exchange data between different threads
	private Handler handler;
	private Handler mainHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Toast.makeText(MainActivity.this, String.valueOf(msg.what),
						Toast.LENGTH_SHORT).show();
			}
		};
		new MyLooper().start();
		new Worker().start();
		new Worker().start();
		new Worker().start();
	}

	// Has Handler in own thread, will batch update the main Activity
	private class MyLooper extends Thread {
		private int count = 0;
		private int border = 100;

		@Override
		public void run() {
			// Prepare the current thread to loop for events
			Looper.prepare();
			// Create a handler bound to this thread
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					Log.i("HANDLER", "MyLooper called");
					Bundle data = msg.getData();
					if (data != null) {
						count++;
						if (count >= border) {
							mainHandler.sendEmptyMessage(count);
							count = 0;
						}
					}
				}
			};
			Looper.loop();
			super.run();
		}
	}

	private class Worker extends Thread {
		@Override
		public void run() {
			while (true) {
				Message message = new Message();
				Bundle bundle = new Bundle();
				bundle.putString("text", "New data");
				message.setData(bundle);
				handler.sendMessage(message);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}