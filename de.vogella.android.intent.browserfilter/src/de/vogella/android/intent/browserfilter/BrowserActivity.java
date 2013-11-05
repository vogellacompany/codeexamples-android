package de.vogella.android.intent.browserfilter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

public class BrowserActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// To keep this example simple, we allow network access
		// in the user interface thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.main);
		Intent intent = getIntent();
		TextView text = (TextView) findViewById(R.id.textView);
		// To get the action of the intent use
		String action = intent.getAction();
		if (action != Intent.ACTION_VIEW) {
			throw new RuntimeException("Should not happen");
		}
		// To get the data use
		Uri data = intent.getData();
		URL url;
		try {
			url = new URL(data.getScheme(), data.getHost(), data.getPath());
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				text.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}