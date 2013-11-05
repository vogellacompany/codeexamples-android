package de.vogella.android.animation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		overridePendingTransition(R.anim.out, R.anim.in);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}