package de.vogella.android.intentservice.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;

public class DownloadService extends IntentService {

	private int result = Activity.RESULT_CANCELED;

	public DownloadService() {
		super("DownloadService");
	}

	// Will be called asynchronously be Android
	@Override
	protected void onHandleIntent(Intent intent) {
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
			// TODO get your messager via the extras
			Message msg = Message.obtain();
			msg.arg1 = result;
			Bundle bundle = new Bundle();
			bundle.putString("absolutePath", output.getAbsolutePath());
			msg.setData(bundle);
			// TODO Send message ensure to put it into a try catch

		}
	}
}
