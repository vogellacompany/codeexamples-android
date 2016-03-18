package de.vogella.android.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class CallNotification extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button button = new Button(this);
		button.setText("Create Notification");
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createNotification();
			}
		});
		setContentView(button);
	}

	public void createNotification() {

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		for (int i = 0; i <= 10; i++) {

			Notification notification = new Notification(R.drawable.icon,
					"A new notification", System.currentTimeMillis());
			// Wir setzen das Flag, dass sie Notification nach Selektion
			// verschwindet
			notification.flags |= Notification.FLAG_AUTO_CANCEL;

			RemoteViews view = new RemoteViews(getPackageName(), R.layout.main);
			int done = i * 10;
			view.setProgressBar(R.id.progressBar1, 100, done, false);
			view.setTextViewText(R.id.textView1,
					"Das ist die Nachricht. Wir sind " + done + " % fertig");
			notification.contentView = view;

			Intent intent = new Intent(this, TargetActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
					intent, PendingIntent.FLAG_CANCEL_CURRENT);
			notification.contentIntent = pendingIntent;
			notificationManager.notify(0, notification);

			// Wir simulieren mal eine lange Aktion
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
