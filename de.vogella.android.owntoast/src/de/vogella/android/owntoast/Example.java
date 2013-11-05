package de.vogella.android.owntoast;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Example extends Activity {
	private View toastLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LayoutInflater inflater = getLayoutInflater();
		toastLayout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.toastlayout));
		TextView message = (TextView) toastLayout.findViewById(R.id.message);
		message.setText("This is my own message");
	}

	public void showMessage(View view) {
		Toast toast = new Toast(this);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(toastLayout);
		toast.show();
	}
}