package de.vogella.android.plurals;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PluralActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void showResult(View view) {
		EditText text = (EditText) findViewById(R.id.editText1);
		int number = Integer.parseInt(text.getText().toString());
		System.out.println(number);
		Resources res = getResources();
		String quantityString = res.getQuantityString(R.plurals.tutorials,
				number, number);

		TextView result = (TextView) findViewById(R.id.result);
		result.setText("Today I read " + quantityString + " at vogella.de");
	}
}