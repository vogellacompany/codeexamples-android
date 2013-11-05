package com.example.android.test;

import android.app.Activity;
import android.os.Bundle;

public class ResultActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rotekarte);
		System.out.println("Hallo");
	}

}
