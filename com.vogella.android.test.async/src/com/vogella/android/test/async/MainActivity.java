package com.vogella.android.test.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private IJobListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public static interface IJobListener {
		void executionDone();
	}

	public void setListener(IJobListener listener) {
		this.listener = listener;
	}

	public void onClick(View view) {
		myTask.execute("test");
	}

	final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

		@Override
		protected String doInBackground(String... arg0) {
			return "Long running stuff";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// TODO nutze Ergebnis...
			
			// Inform listner
			if (listener != null) {
				listener.executionDone();
			}
		}

	};

}
