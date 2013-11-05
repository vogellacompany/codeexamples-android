package de.vogella.android.widget.followers;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class TwitterFollowerReceiver extends AppWidgetProvider {
	static final String TAG = "TwitterFollower";

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.e(TAG, "onEnabled Called");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		Log.i(TAG, "onUpdate Called with " + appWidgetIds.length);
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.i("TwitterFollower", "onDeleted Called");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.i(TAG, "onDisabled Called");
		super.onDisabled(context);
	}

	static void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId) {
		Log.e(TAG, "updateAppWidget Called with " + appWidgetId);
		// Build the intent to call the service
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget_layout);
		Intent intent = new Intent(context.getApplicationContext(),
				TwitterFollowerService.class);

		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		// Set unique data for each pending intent, otherwise the system will
		// replace the existing pending intent
		intent.setAction(String.valueOf(appWidgetId));
		// To react to a click we have to use a pending intent as the
		// onClickListener is
		// excecuted by the homescreen application
		PendingIntent pendingIntent = PendingIntent.getService(
				context.getApplicationContext(), 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);

		// Tell the widget manager
		appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		// Update the widgets via the service
		context.startService(intent);
	}
}
