package de.vogella.android.webview.javascript;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	private static String PROVIDER = LocationManager.GPS_PROVIDER;
	private WebView browser;
	private LocationManager myLocationManager = null;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		browser = (WebView) findViewById(R.id.webkit);
		myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.addJavascriptInterface(new Locater(), "locater");
		browser.loadUrl("file:///android_asset/geoweb1.html");
	}

	@Override
	public void onResume() {
		super.onResume();
		myLocationManager.requestLocationUpdates(PROVIDER, 10000, 100.0f,
				onLocation);
	}

	@Override
	public void onPause() {
		super.onPause();
		myLocationManager.removeUpdates(onLocation);
	}

	LocationListener onLocation = new LocationListener() {
		public void onLocationChanged(Location location) {
			// Calling back to JavaScript in case the location changes
			StringBuilder buf = new StringBuilder("javascript:whereami(");
			buf.append(String.valueOf(location.getLatitude()));
			buf.append(",");
			buf.append(String.valueOf(location.getLongitude()));
			buf.append(")");
			browser.loadUrl(buf.toString());
		}

		public void onProviderDisabled(String provider) {
			// required for interface, not used
		}

		public void onProviderEnabled(String provider) {
			// required for interface, not used
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// required for interface, not used
		}
	};

	public class Locater {
		public String getLocation() throws JSONException {
			Location loc = myLocationManager.getLastKnownLocation(PROVIDER);
			if (loc == null) {
				return (null);
			}
			JSONObject json = new JSONObject();
			json.put("lat", loc.getLatitude());
			json.put("lon", loc.getLongitude());
			return (json.toString());
		}
	}
}
