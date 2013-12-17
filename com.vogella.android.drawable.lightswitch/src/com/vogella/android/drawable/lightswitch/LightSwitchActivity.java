package com.vogella.android.drawable.lightswitch;


import android.R;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class LightSwitchActivity extends Activity {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		final ImageView image = (ImageView) findViewById(R.id.image);
		final ToggleButton button = (ToggleButton) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				TransitionDrawable drawable = (TransitionDrawable) image
						.getDrawable();
				if (button.isChecked()) {
					drawable.startTransition(1000);
				} else {
					drawable.reverseTransition(1000);
				}
			}
		});
	}
}