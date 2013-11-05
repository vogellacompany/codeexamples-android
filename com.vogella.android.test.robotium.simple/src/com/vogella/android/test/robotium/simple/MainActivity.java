package com.vogella.android.test.robotium.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.updateText:
			TextView target = (TextView) findViewById(R.id.target);
			target.setText("Current time in ms:" + System.currentTimeMillis());
			break;
		case R.id.startActivity:
			Intent intent = new Intent(this, NextActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
