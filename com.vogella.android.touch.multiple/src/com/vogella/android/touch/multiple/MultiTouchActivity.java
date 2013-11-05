package com.vogella.android.touch.multiple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MultiTouchActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MultiTouchEventView(this, null));
	}

}
