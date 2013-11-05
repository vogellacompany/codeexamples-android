package com.vogella.android.customview.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

	private static float STROKE_WIDTH = 20f;

	/** Need to track this so the dirty region can accommodate the stroke. **/
	private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;

	private Paint paint = new Paint();
	private List<Path> paths = new ArrayList<Path>();
	private List<Paint> paints = new ArrayList<Paint>();

	/**
	 * Optimizes painting by invalidating the smallest possible area.
	 */
	private float lastTouchX;
	private float lastTouchY;
	private final RectF dirtyRect = new RectF();

	private int[] colors;

	private Path path;

	private int nextColor;

	private Random random;

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);

		colors = new int[] { Color.BLUE, Color.CYAN, Color.GREEN,
				Color.MAGENTA, Color.YELLOW, Color.RED, Color.WHITE };
		random = new Random();
		nextColor = random.nextInt(colors.length);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(STROKE_WIDTH);
	}

	/**
	 * Erases the signature.
	 */
	public void clear() {
		for (Path path : paths) {
			path.reset();
		}
		// Repaints the entire view.
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawARGB(50, 255, 0, 0);
		int i = 0;
		for (Path path : paths) {
			canvas.drawPath(path, paints.get(i++));
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float eventX = event.getX();
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(eventX, eventY);
			return true;

		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_UP:

			path.lineTo(x, y);

			// After replaying history, connect the line to the touch point.
			path.lineTo(eventX, eventY);
			break;

		default:
			return false;
		}

		// Include half the stroke width to avoid clipping.
		invalidate();

		lastTouchX = eventX;
		lastTouchY = eventY;

		return true;
	}

}
