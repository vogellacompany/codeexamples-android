package de.vogella.android.deviceinfo;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.widget.Toast;

public class ShowDeviceInfo extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String deviceId = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		Toast.makeText(this, deviceId, Toast.LENGTH_SHORT).show();
	}
}