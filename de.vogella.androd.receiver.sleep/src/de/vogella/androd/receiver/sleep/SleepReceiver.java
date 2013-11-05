package de.vogella.androd.receiver.sleep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SleepReceiver extends BroadcastReceiver {

	public static boolean wasScreenOn = true;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			// do whatever you need to do here
			wasScreenOn = false;
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			// and do whatever you need to do here
			wasScreenOn = true;
		}
	}

}
