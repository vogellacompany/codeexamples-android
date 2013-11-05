package de.vogella.android.intentservice.download;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			Bundle data = message.getData();
			if (message.arg1 == RESULT_OK && data != null) {

				String path = data.getString("absolutePath");
				Toast.makeText(MainActivity.this, "Downloaded" + path,
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(MainActivity.this, "Download failed.",
						Toast.LENGTH_LONG).show();
			}

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void onClick(View view) {
		Intent intent = new Intent(this, DownloadService.class);
		// Create a new Messenger for the communication back
		Messenger messenger = new Messenger(handler);
		intent.putExtra("MESSENGER", messenger);
		intent.setData(Uri.parse("http://www.vogella.de/index.html"));
		intent.putExtra("urlpath", "http://www.vogella.de/index.html");
		startService(intent);
	}

	public void showToast(View view) {
		Toast.makeText(this, "Still interactive", Toast.LENGTH_SHORT).show();
	}
}