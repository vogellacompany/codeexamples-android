package de.vogella.android.locationapi.maps;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem> {

	private static int maxNum = 5;
	private OverlayItem overlays[] = new OverlayItem[maxNum];
	private int index = 0;
	private boolean full = false;
	private Context context;
	private OverlayItem previousoverlay;

	public MyOverlays(Context context, Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return overlays[i];
	}

	@Override
	public int size() {
		if (full) {
			return overlays.length;
		} else {
			return index;
		}

	}

	public void addOverlay(OverlayItem overlay) {
		if (previousoverlay != null) {
			if (index < maxNum) {
				overlays[index] = previousoverlay;
			} else {
				index = 0;
				full = true;
				overlays[index] = previousoverlay;
			}
			index++;
			populate();
		}
		this.previousoverlay = overlay;
	}

	protected boolean onTap(int index) {
		OverlayItem overlayItem = overlays[index];
		Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("This will end the activity");
		builder.setCancelable(true);
		builder.setPositiveButton("I agree", new OkOnClickListener());
		builder.setNegativeButton("No, no", new CancelOnClickListener());
		AlertDialog dialog = builder.create();
		dialog.show();
		return true;
	};

	private final class CancelOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(context, "You clicked yes", Toast.LENGTH_LONG)
					.show();
		}
	}

	private final class OkOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(context, "You clicked no", Toast.LENGTH_LONG).show();
		}
	}
}