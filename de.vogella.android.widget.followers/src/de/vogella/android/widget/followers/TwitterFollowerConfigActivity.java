package de.vogella.android.widget.followers;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TwitterFollowerConfigActivity extends Activity {
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	public static final String PREFS_NAME = "de.vogella.android.widget.followers";
	public static final String PREF_PREFIX_KEY = "twitteruser_";
	private TextView user;
	static final String TAG = "TwitterFollower";
	private int[] appWidgetIds;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e("TwitterFollower", "onCreate of ConfigActivity called");
		super.onCreate(savedInstanceState);
		// Set the result to CANCELED. This will cause the widget host to cancel
		// out of the widget placement if they press the back button.
		setResult(RESULT_CANCELED);

		setContentView(R.layout.configuration);

		// Find the widget id from the intent.
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		Log.e("TwitterFollower", "WidgetId " + mAppWidgetId);
		// If they gave us an intent without the widget id, just bail.
		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			finish();
		}

		Button button = (Button) findViewById(R.id.finish);
		user = (TextView) findViewById(R.id.twitteruser);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final Context context = TwitterFollowerConfigActivity.this;
				String userName = user.getText().toString();
				userName = userName.replaceAll("@", "");
				saveTitlePref(context, mAppWidgetId, userName);
				Log.e("TwitterFollower", "Config Saved Preference");
				// Push widget update to surface with newly set prefix
				AppWidgetManager appWidgetManager = AppWidgetManager
						.getInstance(context);
				Log.e(TAG, "Update Widget from config");

				// Trigger the update
				TwitterFollowerReceiver.updateAppWidget(
						TwitterFollowerConfigActivity.this, appWidgetManager,
						mAppWidgetId);

				// Make sure we pass back the original appWidgetId
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
						mAppWidgetId);
				setResult(RESULT_OK, resultValue);

				finish();
			}

		});

	}

	// Write the prefix to the SharedPreferences object for this widget
	static void saveTitlePref(Context context, int appWidgetId, String text) {
		SharedPreferences.Editor prefs = context.getSharedPreferences(
				PREFS_NAME, 0).edit();
		prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
		Log.e("TwitterFollower", "Config "
				+ TwitterFollowerConfigActivity.PREF_PREFIX_KEY + appWidgetId);
		prefs.commit();
	}

}
