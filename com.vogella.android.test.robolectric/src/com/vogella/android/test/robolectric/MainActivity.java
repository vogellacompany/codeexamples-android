package com.vogella.android.test.robolectric;

import android.app.Activity;
import android.content.Intent;
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

	public void onClick (View view){
		switch (view.getId()) {
		
		case R.id.button1:
			Toast.makeText(this, "Lala", Toast.LENGTH_LONG).show();
			break;
		case R.id.button2:
			Intent intent = new Intent(this, SecondActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
