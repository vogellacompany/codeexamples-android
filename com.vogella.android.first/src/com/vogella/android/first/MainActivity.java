package com.vogella.android.first;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (BuildConfig.DEBUG) {
			Log.d(Constants.LOG, "onCreated called");
		}
		setContentView(R.layout.activity_main);
	}

	// you may have here an onCreateOptionsMenu method
	// this method is not required for this exercise
	// therefore I deleted it

	public void onClick(View view) {
		EditText input = (EditText) findViewById(R.id.main_input);
		String string = input.getText().toString();
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}
}
