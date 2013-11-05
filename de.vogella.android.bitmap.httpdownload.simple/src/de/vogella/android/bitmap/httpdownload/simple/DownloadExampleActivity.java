package de.vogella.android.bitmap.httpdownload.simple;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DownloadExampleActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void downloadPicture(View view) {
		final ProgressDialog dialog = ProgressDialog.show(this, "Download",
				"downloading");
		dialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					final Bitmap downloadBitmap = downloadBitmap("http://www.vogella.de/img/lars/LarsVogelArticle7.png");
					final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							imageView.setImageBitmap(downloadBitmap);
						}
					});

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					dialog.dismiss();
				}
			}
		}).start();

	}

	private Bitmap downloadBitmap(String url) throws IOException {
		HttpUriRequest request = new HttpGet(url.toString());
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(request);

		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			byte[] bytes = EntityUtils.toByteArray(entity);

			Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0,
					bytes.length);
			return bitmap;
		} else {
			throw new IOException("Download failed, HTTP response code "
					+ statusCode + " - " + statusLine.getReasonPhrase());
		}
	}
}