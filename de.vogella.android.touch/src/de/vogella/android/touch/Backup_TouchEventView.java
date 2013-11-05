package de.vogella.android.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Backup_TouchEventView extends View {
	private Paint paint = new Paint();
	private Path path = new Path();

	public Backup_TouchEventView(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(5f);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			break;
		case MotionEvent.ACTION_UP:
			// nothing to do
			break;
		default:
			return false;
		}
		invalidate();
		return true;
	}
}
