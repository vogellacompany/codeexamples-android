package de.vogella.android.sensor.compass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCompassView extends View {

	private Paint paint;
	private float position = 0;

	public MyCompassView(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(2);
		paint.setTextSize(25);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int xPoint = getMeasuredWidth() / 2;
		int yPoint = getMeasuredHeight() / 2;

		float radius = (float) (Math.max(xPoint, yPoint) * 0.6);
		canvas.drawCircle(xPoint, yPoint, radius, paint);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

		// 3.143 is a good approximation for the circle
		canvas.drawLine(
				xPoint,
				yPoint,
				(float) (xPoint + radius
						* Math.sin((double) (-position) / 180 * 3.143)),
				(float) (yPoint - radius
						* Math.cos((double) (-position) / 180 * 3.143)), paint);

		canvas.drawText(String.valueOf(position), xPoint, yPoint, paint);

	}

	public void updateData(float position) {
		this.position = position;
		invalidate();
	}

}