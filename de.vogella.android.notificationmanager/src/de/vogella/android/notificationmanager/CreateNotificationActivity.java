package de.vogella.android.notificationmanager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateNotificationActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void createNotification(View view) {
		// Prepare intent which is triggered if the
		// notification is selected
		Intent intent = new Intent(this, NotificationReceiverActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

		String longText = "A PendingIntent is a token that you give to another application (e.g. Notification Manager, Alarm Manager or other 3rd party applications), which allows this other application to use the permissions of your application to execute a predefined piece of code.To perform a broadcast via a pending intent so get a PendingIntent via PendingIntent.getBroadcast(). To perform an activity via an pending intent you receive the activity via PendingIntent.getActivity().";
		// Build notification
		// Actions are just fake
		Notification n = new Notification.Builder(this)
				.setContentTitle("New mail from " + "test@gmail.com")
				.setContentText("Subject")
				.setSmallIcon(R.drawable.ic_stat_name)
				.addAction(R.drawable.ic_stat_name, "Call", pIntent)
				.addAction(R.drawable.ic_stat_name, "Dismiss", pIntent)
				.addAction(R.drawable.ic_stat_name, "No idea", pIntent)
				.setStyle(new Notification.BigTextStyle().bigText("Long text"))
				.setContentIntent(pIntent).build();

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Hide the notification after its selected

		notificationManager.notify(0, n);

	}
}
