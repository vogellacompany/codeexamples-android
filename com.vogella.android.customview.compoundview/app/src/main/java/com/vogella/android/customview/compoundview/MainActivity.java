package com.vogella.android.customview.compoundview;

import com.vogella.android.view.compoundview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClicked(View view) {
		String text = view.getId() == R.id.view1 ? "Background" : "Foreground";
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

}
