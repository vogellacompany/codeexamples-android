package com.vogella.android.lifecycle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends TracerActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

}
