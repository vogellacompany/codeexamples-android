package de.vogella.android.intentservice.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.StrictMode;
import android.util.Log;

/* This is an INCORRECT implementation for the purpose of demonstration 
 * Please do not use
 */
public class WrongDownloadService extends Service {

	private int result = Activity.RESULT_CANCELED;

	public WrongDownloadService() {
		super();
	}

	// Runs in the main user interface thread
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Don't do this
		// This will run in the main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		Uri data = intent.getData();
		String urlPath = intent.getStringExtra("urlpath");
		String fileName = data.getLastPathSegment();
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
			InputStreamReader reader = new InputStreamReader(stream);
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

		Bundle extras = intent.getExtras();
		if (extras != null) {
			Messenger messenger = (Messenger) extras.get("MESSENGER");

			Message msg = Message.obtain();
			msg.arg1 = result;
			Bundle bundle = new Bundle();
			bundle.putString("absolutePath", output.getAbsolutePath());
			msg.setData(bundle);
			try {
				messenger.send(msg);
			} catch (android.os.RemoteException e1) {
				Log.w(getClass().getName(), "Exception sending message", e1);
			}

		}
		return Service.START_NOT_STICKY;

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
