package de.vogella.android.c2dm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String string = extras.getString("message");
			TextView view = (TextView) findViewById(R.id.c2dm);
			view.setText(string);
		}
	}
}
