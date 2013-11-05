package de.vogella.android.codelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CodeLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		TextView text = new TextView(this);
		text.setText("Hello");
		text.setLayoutParams(params1);
		EditText edit = new EditText(this);
		edit.setHint("This is your input...");
		edit.setLayoutParams(params1);
		Button button = new Button(this);
		button.setText("Press me");
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast makeText = Toast.makeText(v.getContext(), "Pressed", 200);
				makeText.show();
			}
		});
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(10, 10, 10, 10);
		layout.addView(text);
		layout.addView(edit);
		layout.addView(button);
		setContentView(layout);
	}
}