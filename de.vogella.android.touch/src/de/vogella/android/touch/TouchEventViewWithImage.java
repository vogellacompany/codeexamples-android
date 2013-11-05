package de.vogella.android.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventViewWithImage extends View {
	private Paint paint = new Paint();
	private Path path = new Path();
	private Point point = new Point();
	private Bitmap bitmap;

	public TouchEventViewWithImage(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(5f);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (bitmap != null) {
			canvas.drawBitmap(bitmap, new Matrix(), null);
		}
		canvas.drawPath(path, paint);
		canvas.drawCircle((float) point.x, (float) point.y, 100, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			point.set((int) x, (int) y);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			break;
		default:
			return false;
		}
		invalidate();
		return true;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		invalidate();
	}

}
