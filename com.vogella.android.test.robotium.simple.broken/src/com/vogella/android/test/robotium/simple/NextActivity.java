package com.vogella.android.test.robotium.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderentry);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.okClicked:
			Toast.makeText(this, "Ok pressed", Toast.LENGTH_LONG).show();
			break;
		case R.id.cancelClicked:
			Toast.makeText(this, "Canceled pressed", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
	}
}
