package de.vogella.android.touch.scaledetector;

import android.app.Activity;
import android.os.Bundle;

public class ScaleDetectorTest extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ImageViewWithZoom(this));
	}
}