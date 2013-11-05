package com.example.android.test;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TestAppActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void startTimer(View View) {
		// Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
		EditText editText = (EditText) findViewById(R.id.duration);
		String string = editText.getText().toString();
		Integer duration = Integer.valueOf(string);
		AlarmManager service = (AlarmManager) getSystemService(ALARM_SERVICE);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, ResultActivity.class), 0);
		service.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000
				* duration, pendingIntent);
	}
}