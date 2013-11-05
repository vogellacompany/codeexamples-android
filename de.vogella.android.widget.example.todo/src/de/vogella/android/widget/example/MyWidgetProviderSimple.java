package de.vogella.android.widget.example;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProviderSimple extends AppWidgetProvider {

	private static final String ACTION_CLICK = "ACTION_CLICK";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO 0 Make it possible to add the simple widget to the homescreen
		// via Android Manifest

		// TODO 1 Get the component name for MyWidgetProviderSimple.class
		// instead of Void.class
		ComponentName thisWidget = new ComponentName(context, Void.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			// Create some random data
			int number = (new Random().nextInt(100));

			// TODO 2 Use widgetsimple_layout.xml as layout instead of -1
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					-1);
			Log.w("WidgetExample", String.valueOf(number));
			// TODO 3 Set the text to the view with the id R.id.update
			// instead of -1
			remoteViews.setTextViewText(-1, String.valueOf(number));

			// Register an onClickListener
			Intent intent = new Intent(context, MyWidgetProviderSimple.class);

			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			// TODO 4 Add pending intent to the text view with the id
			// R.id.update
			// remoteViews.setOnClickPendingIntent();

			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
}