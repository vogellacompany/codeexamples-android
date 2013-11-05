package de.vogella.android.touch.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchMoveMeView extends View {
	private Drawable mIcon;
	private float mPosX;
	private float mPosY;

	private float mLastTouchX;
	private float mLastTouchY;

	public SingleTouchMoveMeView(Context context) {
		this(context, null, 0);
	}

	public SingleTouchMoveMeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SingleTouchMoveMeView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		mIcon = context.getResources().getDrawable(R.drawable.ic_launcher);
		mIcon.setBounds(0, 0, mIcon.getIntrinsicWidth(),
				mIcon.getIntrinsicHeight());
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.save();
		canvas.translate(mPosX, mPosY);
		mIcon.draw(canvas);
		canvas.restore();
	}

	// Let the user move the icon around
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			// Remember where we started
			mLastTouchX = x;
			mLastTouchY = y;
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final float x = ev.getX();
			final float y = ev.getY();

			// Calculate the distance moved
			final float dx = x - mLastTouchX;
			final float dy = y - mLastTouchY;

			// Move the object
			mPosX += dx;
			mPosY += dy;

			// Remember this touch position for the next move event
			mLastTouchX = x;
			mLastTouchY = y;

			// Invalidate to request a redraw
			invalidate();
			break;
		}
		}

		return true;
	}
}