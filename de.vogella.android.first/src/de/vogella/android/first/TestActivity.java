package de.vogella.android.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class TestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (BuildConfig.DEBUG) {
			Log.e(Constants.LOG, "onCreate called");
		}
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

	}

	public void shareData(View view) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "This is my shared text");
		startActivity(Intent.createChooser(intent, "Share this text via"));
	}

	public void sayHello(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		text.getText().toString();
		Toast.makeText(this, "Button 1 pressed", Toast.LENGTH_LONG).show();
	}
}