package de.vogella.android.handler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressTestActivity extends Activity {
	private ProgressBar progress;
	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		text = (TextView) findViewById(R.id.textView1);

	}

	public void startProgress(View view) {
		// Do something long
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					final int value = i;
					 doFakeWork();
					progress.post(new Runnable() {
						@Override
						public void run() {
							text.setText("Updating");
							progress.setProgress(value);
						}
					});
				}
			}
		};
		new Thread(runnable).start();
	}

	// Simulating something timeconsuming
	private void doFakeWork() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}