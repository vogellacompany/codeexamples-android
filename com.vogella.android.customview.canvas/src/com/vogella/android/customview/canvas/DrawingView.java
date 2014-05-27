package com.vogella.android.customview.canvas;

import java.util.ArrayList;
import java.util.Iterator;
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

	private List<Path> paths = new ArrayList<Path>();
	private List<Paint> paints = new ArrayList<Paint>();

	/**
	 * Optimizes painting by invalidating the smallest possible area.
	 */
	private float lastTouchX;
	private float lastTouchY;
	private final RectF dirtyRect = new RectF();

	private int[] colors;

	private Path path = new Path();

	private int nextColor;

	private Random random;

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);

		colors = new int[] { Color.BLUE, Color.CYAN, Color.GREEN,
				Color.MAGENTA, Color.YELLOW, Color.RED, Color.WHITE };

		random = new Random();
		nextColor = random.nextInt(colors.length);
		for (int i = 0; i < colors.length; i++) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(colors[i]);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
			paints.add(paint);
		}

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
		if (isInEditMode()) {
			canvas.drawARGB(255, 255, 0, 0);
		}
		super.onDraw(canvas);
		int i = 0;
		for (Path path : paths) {
			canvas.drawPath(path, paints.get(i++));
			if (i == paints.size()) {
				i = 0;
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float eventX = event.getX();
		float eventY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path = new Path();
			paths.add(path);
			path.moveTo(eventX, eventY);
			return true;

		case MotionEvent.ACTION_MOVE:
			path.lineTo(eventX, eventY);
		case MotionEvent.ACTION_UP:
			// After replaying history, connect the line to the touch point.
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
