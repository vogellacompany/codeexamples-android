package de.vogella.android.locationapi.pathfinder;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Pathfinder extends Activity {
	private EditText edit1;
	private EditText edit2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edit1 = (EditText) findViewById(R.id.EditText01);
		edit2 = (EditText) findViewById(R.id.EditText02);

	}

	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			System.out.println("Called");
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			edit1.setText(lat);
			edit2.setText(lng);
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	public void showLocation(View view) {
		switch (view.getId()) {
		case R.id.Button01:
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				System.out.println("not null");
			} else {
				System.out.println("null");
			}
			// int lat = (int) (location.getLatitude() * 1E6);
			// int lng = (int) (location.getLongitude() * 1E6);
			// edit1.setText(lat);
			// edit2.setText(lng);
			break;
		}

	}

}