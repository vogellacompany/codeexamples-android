package de.vogella.android.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class LayoutTester extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		RadioGroup group1 = (RadioGroup) findViewById(R.id.orientation);
		group1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.horizontal:
					group.setOrientation(LinearLayout.HORIZONTAL);
					break;
				case R.id.vertical:
					group.setOrientation(LinearLayout.VERTICAL);
					break;
				}
			}
		});
		RadioGroup group2 = (RadioGroup) findViewById(R.id.gravity);
		group2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.left:
					group.setGravity(Gravity.LEFT);
					break;
				case R.id.center:
					group.setGravity(Gravity.CENTER);
					break;
				case R.id.right:
					group.setGravity(Gravity.RIGHT);
					break;
				}
			}
		});
	}
}