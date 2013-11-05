package de.vogella.android.locationapi;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CurrentLocation extends MapActivity {

	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private MyOverlays itemizedoverlay;
	private List<Overlay> overlays;
	public static final int INSERT_ID = Menu.FIRST;
	public static final int CENTER_ID = Menu.FIRST + 1;
	public static final int SATELLITE_ID = Menu.FIRST + 2;
	public static final int MAP_ID = Menu.FIRST + 3;
	private Location location;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.main); // bind the layout to the activity

		// create a map view
		RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.mainlayout);
		mapView = (MapView) findViewById(R.id.mapview);

//		ZoomControls mZoom = (ZoomControls) mapView.getZoomControls();
//		linearLayout.addView(mZoom);
		mapController = mapView.getController();
		// Zoon 1 is world view
		mapController.setZoom(14);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, new GeoUpdateHandler());

		overlays = mapView.getOverlays();
		Drawable drawable = this.getResources()
				.getDrawable(R.drawable.push_pin);
		itemizedoverlay = new MyOverlays(drawable);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_add);
		menu.add(0, CENTER_ID, 0, R.string.menu_location);
		menu.add(0, SATELLITE_ID, 0, R.string.menu_satellite);
		menu.add(0, MAP_ID, 0, R.string.menu_map);
		return result;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createMarker();
			return true;
		case CENTER_ID:
			centerMap();
			return true;
		case SATELLITE_ID:
			satelliteMap(true);
			return true;
		case MAP_ID:
			satelliteMap(false);
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	private void satelliteMap(boolean satellite) {
		if (satellite) {
			mapView.setSatellite(true);
		} else {
			mapView.setSatellite(false);
		}
	}

	private void centerMap() {
		mapController.animateTo(getGeoPoint());
		// mapController.setCenter();
	}

	private void createMarker() {
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		overlays.add(itemizedoverlay);
	}

	public GeoPoint getGeoPoint() {
		int lat = (int) (location.getLatitude() * 1E6);
		int lng = (int) (location.getLongitude() * 1E6);
		GeoPoint point = new GeoPoint(lat, lng);
		return point;

	}

	public class GeoUpdateHandler implements LocationListener {

		@Override
		public void onLocationChanged(Location newLocation) {
			System.out.println("Called");
			location = newLocation;
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
}