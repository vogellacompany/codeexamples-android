
package com.vogella.android.touch.multiple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchEventView extends View {
	private Paint paint = new Paint();
	private Path path = new Path();

	public MultiTouchEventView(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint.setAntiAlias(true);
		paint.setStrokeWidth(6f);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		

		int action = event.getAction();
		if (event.getPointerCount()>1){
			int actionEvent = action & MotionEvent.ACTION_MASK;
			int actionPointerId= action & MotionEvent.ACTION_POINTER_INDEX_MASK;
			
			// Find the index of the pointer
			int index = event.findPointerIndex(actionPointerId);
			
			// Gets its coordinates
			float eventX = event.getX(index);
			float eventY = event.getY(index);
			
			// TODO Do something with the data
			
		}

		// Schedules a repaint.
		invalidate();
		return true;
	}
}
