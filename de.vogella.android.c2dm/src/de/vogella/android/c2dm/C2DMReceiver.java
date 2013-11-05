package de.vogella.android.c2dm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.c2dm.C2DMBaseReceiver;

public class C2DMReceiver extends C2DMBaseReceiver {
	public C2DMReceiver() {
		// Email address currently not used by the C2DM Messaging framework
		super("dummy@google.com");
	}

	@Override
	public void onRegistered(Context context, String registrationId)
			throws java.io.IOException {
		Log.e("C2DM", "Registration ID received");
		Log.e("C2DM", registrationId);
		Intent intent = new Intent(context, ResultActivity.class);
		intent.putExtra("message", "Registration ID received");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	};

	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.e("C2DM", "Neue Message.");
		Intent resultIntent = new Intent(context, ResultActivity.class);
		resultIntent.putExtra("message", "Message received");
		resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	@Override
	public void onError(Context context, String errorId) {
		Log.e("C2DM", "Error occured!!!");
		Log.e("C2DM", errorId);
	}

}
