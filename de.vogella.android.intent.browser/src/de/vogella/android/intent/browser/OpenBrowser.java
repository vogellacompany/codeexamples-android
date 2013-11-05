package de.vogella.android.intent.browser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class OpenBrowser extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void openBrowser(View view) {
		Intent i = new Intent("android.intent.action.VIEW",
				Uri.parse("http://www.vogella.de"));
		startActivity(i);
	}
}
