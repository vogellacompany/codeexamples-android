package de.vogella.android.battery;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		// Register for the battery changed event
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
Intent
		// Intent is sticky so using null as receiver works fine
		// return value contains the status
		Intent batteryStatus = this.registerReceiver(null, filter);

		// Are we charging / charged?
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
				|| status == BatteryManager.BATTERY_STATUS_FULL;

		boolean isFull = status == BatteryManager.BATTERY_STATUS_FULL;

		// How are we charging?
		int chargePlug = batteryStatus.getIntExtra(
				BatteryManager.EXTRA_PLUGGED, -1);
		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

		// Update the user interface (Rating bar) based on our findings

		RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);

		if (isFull) {
			bar.setProgress(10);
		} else if (isCharging) {
			bar.setProgress(5);
		}

	}
}