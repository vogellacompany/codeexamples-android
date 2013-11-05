package com.vogella.android.customview.surfaceview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private Ball ball;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		ball = new Ball(this);
		setContentView(ball);
	}

	@Override
	protected void onPause() {
		super.onPause();
		setContentView(null);
		ball = null;
		finish();
	}

}