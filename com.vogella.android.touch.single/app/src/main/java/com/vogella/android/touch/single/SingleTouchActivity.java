package com.vogella.android.touch.single;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SingleTouchActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().s
		setContentView(new SingleTouchEventView(this, null));
		int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
		uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
		uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
		uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		getWindow().getDecorView().setSystemUiVisibility(uiOptions);
	}

}
