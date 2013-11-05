package de.vogella.android.c2dm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.c2dm.C2DMessaging;

public class RegisterActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void register(View view) {
		Log.e("Super", "Starting registration");
		Toast.makeText(this, "Starting", Toast.LENGTH_LONG).show();
		EditText text = (EditText) findViewById(R.id.editText1);

		C2DMessaging.register(this, text.getText().toString());
	}
}