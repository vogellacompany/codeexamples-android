package com.vogella.android.intent.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_result);
		Bundle extras = getIntent().getExtras();
		String inputString = extras.getString("yourkey");
		TextView view = (TextView) findViewById(R.id.displayintentextra);
		view.setText(inputString);
	}

	@Override
	public void finish() {
		Intent intent = new Intent();
		EditText editText= (EditText) findViewById(R.id.returnValue);
		String string = editText.getText().toString();
		intent.putExtra("returnkey", string);
		setResult(RESULT_OK, intent);
		super.finish();
	}
}
