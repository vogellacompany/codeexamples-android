package com.vogella.android.intent.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	// constant to determine which sub-activity returns
	private static final int REQUEST_CODE = 10;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public void onClick(View view) {
		EditText text = (EditText) findViewById(R.id.inputforintent);
		String string = text.getText().toString();
		Intent i = new Intent(this, ResultActivity.class);
		i.putExtra("yourkey", string);
		startActivityForResult(i, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
			if (data.hasExtra("returnkey")) {
				String result = data.getExtras().getString("returnkey");
				if (result != null && result.length() > 0) {
					Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
}