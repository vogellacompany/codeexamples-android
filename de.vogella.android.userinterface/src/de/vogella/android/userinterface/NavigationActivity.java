package de.vogella.android.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavigationActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation);
	}

	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.startListActivity:
			intent = new Intent(this, MyListActivity.class);
			break;
		case R.id.startTouchArea:
			intent = new Intent(this, TouchAreaActivity.class);
			break;
		case R.id.startStyledListActivity:
			intent = new Intent(this, MyStyledListActivity.class);
			break;
		case R.id.startShapeDrawableActivity:
			intent = new Intent(this, ShapeDrawableActivity.class);
			break;
		case R.id.startShineActivity:
			intent = new Intent(this, ShineActivity.class);
			break;
		}
		startActivity(intent);
	};

}