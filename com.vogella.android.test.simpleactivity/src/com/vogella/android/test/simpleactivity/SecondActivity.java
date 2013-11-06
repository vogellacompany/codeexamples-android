package com.vogella.android.test.simpleactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Bundle extras = getIntent().getExtras();
		if (extras!=null& extras.containsKey("TEXT")){
			TextView view = (TextView) findViewById(R.id.resultText);
			view.setText(extras.getString("TEXT"));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
