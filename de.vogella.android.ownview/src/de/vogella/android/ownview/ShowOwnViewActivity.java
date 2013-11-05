package de.vogella.android.ownview;

import android.app.Activity;
import android.os.Bundle;

public class ShowOwnViewActivity extends Activity {
	private MyDrawView view;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MyDrawView(this);
		setContentView(view);
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause");

	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume");
		view.onSaveInstanceState();
	}
}