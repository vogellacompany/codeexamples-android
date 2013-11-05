package com.vogella.android.textview.spannablestring;

import android.app.Activity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView) findViewById(R.id.input);
		String header = "This is the header";
		String description = "This is the description";

		Spannable styledText = new SpannableString(header + "\n" + description);
		TextAppearanceSpan span1 = new TextAppearanceSpan(this,
				R.style.textHeader);
		TextAppearanceSpan span2 = new TextAppearanceSpan(this,
				R.style.textbody);
		styledText.setSpan(span1, 0, header.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		styledText.setSpan(span2, header.length() + 1, header.length() + 1
				+ description.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(styledText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
