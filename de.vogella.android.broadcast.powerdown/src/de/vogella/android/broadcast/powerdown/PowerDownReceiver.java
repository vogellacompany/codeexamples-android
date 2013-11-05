package de.vogella.android.broadcast.powerdown;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PowerDownReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_SCREEN_OFF.equalsIgnoreCase(intent.getAction())) {
			System.out.println("Write Preferences");
		}
	}
}
