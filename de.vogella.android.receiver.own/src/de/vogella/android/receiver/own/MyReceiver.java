package de.vogella.android.receiver.own;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "MyReceiver is called", Toast.LENGTH_LONG)
				.show();

	}

}
