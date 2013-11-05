package de.vogella.android.widget.followers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class TwitterFollowerService extends Service {
	static final String TAG = "TwitterFollower";

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i("TwitterFollower", "Service Called");
		SharedPreferences preferences = this.getSharedPreferences(
				TwitterFollowerConfigActivity.PREFS_NAME, 0);

		int appWidgetId = intent.getIntExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);

		if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			return;
		}
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		RemoteViews views = new RemoteViews(this.getPackageName(),
				R.layout.widget_layout);
		views.setTextViewText(R.id.time, "updating...");
		appWidgetManager.updateAppWidget(appWidgetId, views);

		String user = preferences.getString(
				TwitterFollowerConfigActivity.PREF_PREFIX_KEY + appWidgetId,
				"vogella");
		String followerNumber = preferences.getString("followerNumber"
				+ appWidgetId, "unknown");
		String lastUpdated = preferences.getString("lastUpdated" + appWidgetId,
				"not updated");

		Log.i("TwitterFollower", "Saved data. User: " + user + " Follower: "
				+ followerNumber + " lastUpdated " + lastUpdated);
		if (isOnline()) {
			try {
				Log.e(TAG, "we are online");
				JSONObject twitterProfile = new JSONObject(
						readTwitterFeed(user));
				Log.e("TwitterFollower", String.valueOf(twitterProfile
						.getInt("followers_count")));
				followerNumber = String.valueOf(twitterProfile
						.getInt("followers_count"));
				Date dt = new Date();
				int hours = dt.getHours();
				int minutes = dt.getMinutes();
				String sMinutes = String.valueOf(minutes);
				if (sMinutes.length() == 1) {
					sMinutes = "0" + sMinutes;
				}
				lastUpdated = hours + ":" + sMinutes;
				Log.i("TwitterFollower", "New data. User: " + user
						+ " Follower: " + followerNumber + " lastUpdated "
						+ lastUpdated);

			} catch (Exception e) {
				// Download did not work
				e.printStackTrace();
			}
		}
		views = new RemoteViews(this.getPackageName(), R.layout.widget_layout);
		views.setTextViewText(R.id.twitteruser, user);
		views.setTextViewText(R.id.followers, followerNumber);
		views.setTextViewText(R.id.time, lastUpdated);
		PendingIntent pendingIntent = PendingIntent.getService(
				getApplicationContext(), 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.layout, pendingIntent);

		Log.i("TwitterFollower", "Service set new UI for " + appWidgetId);
		appWidgetManager.updateAppWidget(appWidgetId, views);
		Log.i("TwitterFollower", "Service ended");
		stopSelf();
		super.onStart(intent, startId);

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private String readTwitterFeed(String user) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://twitter.com/users/show/" + user
				+ ".json");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e("TwitterFollowerReceiver", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

}
