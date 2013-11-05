package com.vogella.android.actionbar.customviews;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();
		Bitmap b = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);

		actionBar.
			setBackgroundDrawable(new BitmapDrawable(getResources(), b));

		// add the custom view to the action bar

		actionBar.setCustomView(R.layout.actionbar_view);

		EditText search = (EditText) actionBar.getCustomView().findViewById(
				R.id.searchfield);
		search.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				Toast.makeText(MainActivity.this, "Search triggered",
						Toast.LENGTH_LONG).show();
				return false;
			}
		});
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
				| ActionBar.DISPLAY_SHOW_HOME);
	}
}
