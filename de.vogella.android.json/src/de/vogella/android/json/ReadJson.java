package de.vogella.android.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ReadJson extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(
				"http://vogellarest.appspot.com/rest/todo");
		getRequest.setHeader("Accept", "application/json");
		// Use GZIP encoding
		getRequest.setHeader("Accept-Encoding", "gzip"); //
		try {
			HttpResponse response = (HttpResponse) httpclient
					.execute(getRequest);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				Header contentEncoding = response
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				// convert content stream to a String
				String result = readStream(instream);
				instream.close();

				Log.i("JSON", result);
				TextView view = (TextView) findViewById(R.id.result);
				view.setText(result);
				JSONObject jsonObject = new JSONObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String readStream(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}