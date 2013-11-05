package de.vogella.android.touch.multitouch;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;

public class MultitouchActivity extends Activity implements OnTouchListener {

	public static final int MAX_POINTERS = 10;

	private MultiTouchTrackingView touchView;
	Paint paint;
	Paint paintInfoText;

	PointF[] points;
	private int[] lastActions;
	int pointerCount;

	private float displayDensity;
	int radius;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		displayDensity = getResources().getDisplayMetrics().density;

		points = new PointF[MAX_POINTERS];
		lastActions = new int[MAX_POINTERS];

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(calcDevicePixels(15));

		paintInfoText = new Paint();
		paintInfoText.setColor(Color.BLACK);
		paintInfoText.setAntiAlias(true);
		paintInfoText.setTextSize(calcDevicePixels(18));

		touchView = new MultiTouchTrackingView(this, this);
		touchView.setOnTouchListener(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(touchView);

		radius = calcDevicePixels(40);
	}

	public int calcDevicePixels(int deviceIndependentPixel) {
		return (int) (deviceIndependentPixel * displayDensity + 0.5f);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		pointerCount = event.getPointerCount();

		int actionId = event.getPointerId(pointerIndex);
		Log.d("greenrobot", "MotionEvent - pointer ID:  " + actionId
				+ ", action: " + mapActionCodeToString(action)
				+ ", pointer count: " + pointerCount);
		if (actionId < MAX_POINTERS) {
			lastActions[actionId] = action;
		}

		for (int i = 0; i < pointerCount; i++) {
			int pointerId = event.getPointerId(i);
			if (pointerId < MAX_POINTERS) {
				points[pointerId] = new PointF(event.getX(i), event.getY(i));
				if (action == MotionEvent.ACTION_MOVE) {
					lastActions[pointerId] = action;
				}
			}
		}

		touchView.invalidate();
		return true;
	}

	public int getColor(int pointerId) {
		int color;
		switch (lastActions[pointerId]) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			color = 0xaa0000ff; // BLUE
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			color = 0xaa999999; // GREY
			break;
		case MotionEvent.ACTION_MOVE:
			color = 0xaa00ff00; // GREEN
			break;
		default:
			color = 0xaaFF0000; // RED
		}
		return color;
	}

	public String getActionText(int pointerId) {
		String action = mapActionCodeToString(lastActions[pointerId]);
		return pointerId + ": " + action;
	}

	private String mapActionCodeToString(int actionCode) {
		String action;
		switch (actionCode) {
		case MotionEvent.ACTION_DOWN:
			action = "Down";
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			action = "Pointer Down";
			break;
		case MotionEvent.ACTION_UP:
			action = "Up";
			break;
		case MotionEvent.ACTION_POINTER_UP:
			action = "Pointer Up";
			break;
		case MotionEvent.ACTION_MOVE:
			action = "Move";
			break;
		default:
			action = "Other (" + actionCode + ")";
		}
		return action;
	}

}