package com.vogella.android.test.renamingdelegationcontext;

import java.io.FileInputStream;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	public final static String FILE_NAME = "config.txt";

	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);

		final byte[] buffer = new byte[1024];

		try {
			final FileInputStream fis = openFileInput(FILE_NAME);
			final int n = fis.read(buffer);
			tv.setText(new String(buffer, 0, n - 1));

		} catch (Exception e) {
			tv.setText(e.toString());
			tv.setTextColor(Color.RED);
		}

	}

	public String getText() {
		return tv.getText().toString();

	}
}
