package de.vogella.android.intent.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ShareReceiver extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String string = extras.getString(Intent.EXTRA_TEXT);
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}
}
