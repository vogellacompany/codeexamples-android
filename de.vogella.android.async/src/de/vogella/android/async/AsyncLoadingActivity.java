package de.vogella.android.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncLoadingActivity extends Activity {

	private final static class LoadFileAsync extends
			AsyncTask<String, Integer, Void> {

		private AsyncLoadingActivity activity;

		public void setActivity(AsyncLoadingActivity activity) {
			this.activity = activity;
		}

		@Override
		protected Void doInBackground(String... params) { // background
			for (int i = 0; i < 7; i++) {
				try {
					Thread.sleep(1000);
					if (isCancelled())
						break;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) { // UI thread
			TextView text = (TextView) activity.findViewById(R.id.text1);
			text.setText(String.format("%s / 7", values[0] + 1));
		}

		@Override
		protected void onPostExecute(Void result) { // UI thread
			activity.onLoadingDone();
		}
	}

	private LoadFileAsync loader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		loader = (LoadFileAsync) getLastNonConfigurationInstance();
		if (loader != null) {
			Button view = (Button) findViewById(R.id.button1);
			view.setEnabled(false);
			loader.setActivity(this);
		}

		loader.cancel(false);
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return loader;
	}

	public void onClick(View view) throws Exception {
		view.setEnabled(false);
		// loadFile("http://localhost");
		loader = new LoadFileAsync();
		loader.setActivity(this);
		loader.execute("http://localhost");
	}

	private void loadFile(String filePath) throws Exception {
		for (int i = 0; i < 7; i++) {
			Thread.sleep(1000);
			TextView text = (TextView) findViewById(R.id.text1);
			text.setText(String.format("%s / 7", i + 1));
		}
		onLoadingDone();
	}

	private void onLoadingDone() {
		findViewById(R.id.button1).setEnabled(true);
		Toast.makeText(this, "File was loaded", Toast.LENGTH_SHORT).show();
	}

}