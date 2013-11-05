package de.vogella.android.ownservice.messenger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.StrictMode;
import android.util.Log;

public class DownloadServiceMessenger extends Service {
	public static final String FILENAME = "fileName";
	public static final String URLPATH = "urlPath";
	public static final String RESULTPATH = "urlPath";
	private int result = Activity.RESULT_CANCELED;

	// Used to receive messages from the Activity
	final Messenger inMessenger = new Messenger(new IncomingHandler());
	// Use to send message to the Activity
	private Messenger outMessenger;

	public DownloadServiceMessenger() {
		super();
		// Don't do this
		// Network Stuff will run in the main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			Log.e("MESSAGE", "Got message");
			Bundle data = msg.getData();
			String urlPath = data.getString(DownloadServiceMessenger.URLPATH);
			String fileName = data.getString(DownloadServiceMessenger.FILENAME);
			String outputPath = download(urlPath, fileName);

			Message backMsg = Message.obtain();
			backMsg.arg1 = result;
			Bundle bundle = new Bundle();
			bundle.putString(RESULTPATH, outputPath);
			backMsg.setData(bundle);
			try {
				outMessenger.send(backMsg);
			} catch (android.os.RemoteException e1) {
				Log.w(getClass().getName(), "Exception sending message", e1);
			}
		}
	}

	private String download(String urlPath, String fileName) {
		File output = new File(Environment.getExternalStorageDirectory(),
				fileName);
		if (output.exists()) {
			output.delete();
		}

		InputStream stream = null;
		FileOutputStream fos = null;
		try {

			URL url = new URL(urlPath);
			stream = url.openConnection().getInputStream();
			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			fos = new FileOutputStream(output.getPath());
			int next = -1;
			while ((next = reader.read()) != -1) {
				fos.write(next);
			}
			// Sucessful finished
			result = Activity.RESULT_OK;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return output.getAbsolutePath();

	}

	@Override
	public IBinder onBind(Intent intent) {
		Bundle extras = intent.getExtras();
		// Get messager from the Activity
		if (extras != null) {
			outMessenger = (Messenger) extras.get("MESSENGER");
		}
		// Return our messenger to the Activity to get commands
		return inMessenger.getBinder();
	}
}
