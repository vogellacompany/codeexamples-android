package de.vogella.android.fragment.pictures;

import android.app.Activity;
import android.os.Bundle;

public class PictureActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PictureFragment fragment = new PictureFragment();
		getFragmentManager().beginTransaction()
				.add(android.R.id.content, fragment).commit();
	}
}