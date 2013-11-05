package de.vogella.android.c2dm.simpleclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MessageReceivedActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_result);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e("TAG", "onstart");
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Log.e("TAG", "onstart2");
			String message = extras.getString("payload");
			if (message != null && message.length() > 0) {
				TextView view = (TextView) findViewById(R.id.result);
				view.setText(message);
			}
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.e("TAG", "onstart2");
	}
}
