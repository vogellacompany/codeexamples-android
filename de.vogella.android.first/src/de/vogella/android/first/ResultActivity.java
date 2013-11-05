package de.vogella.android.first;

import android.app.Activity;
import android.os.Bundle;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
	}
}
