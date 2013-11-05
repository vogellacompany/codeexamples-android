package de.vogella.android.locationapi;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> overlays = new ArrayList<OverlayItem>();

	public MyOverlays(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlays.get(i);
	}

	@Override
	public int size() {
		return overlays.size();
	}

	/**
	 * Allows to add items from extern. Populate uses the method size() to
	 * determine how many items are available and the createItem to get the
	 * items.
	 * 
	 * @param overlay
	 */
	public void addOverlay(OverlayItem overlay) {
		overlays.add(overlay);
		populate();
	}

}
