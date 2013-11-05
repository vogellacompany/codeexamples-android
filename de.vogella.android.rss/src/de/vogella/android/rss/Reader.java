package de.vogella.android.rss;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.vogella.android.rss.reader.Message;
import de.vogella.android.rss.reader.XmlPullFeedParser;

public class Reader extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.readXML);
		button.setText("Hello");
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Settings.System.putString(getContentResolver(),
						Settings.System.HTTP_PROXY, "proxy:8080");// enable
																	// proxy
				XmlPullFeedParser parser = new XmlPullFeedParser(
						"http://www.vogella.de");
				List<Message> messages = parser.parse();
				System.out.println(messages);

			}
		});
	}
}