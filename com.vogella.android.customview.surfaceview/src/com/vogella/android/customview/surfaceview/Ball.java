package com.vogella.android.customview.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Ball extends SurfaceView implements SurfaceHolder.Callback {

	private BallThread ballThread = null;
	private Bitmap bitmap;

	private float x, y;
	private float vx, vy;

	public Ball(Context context) {
		super(context);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);

		x = 50.0f;
		y = 50.0f;

		vx = 10.0f;
		vy = 10.0f;

		getHolder().addCallback(this);
		ballThread = new BallThread(getHolder(), this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		update(canvas);
		canvas.drawARGB(255, 0, 0, 0);
		canvas.drawBitmap(bitmap, x, y, null);
	}

	public void update(Canvas canvas) {
		checkCollisions(canvas);
		x += vx;
		y += vy;
	}

	public void checkCollisions(Canvas canvas) {
		if (x - vx < 0) {
			vx = Math.abs(vx);
		} else if (x + vx > canvas.getWidth() - getBitmapWidth()) {
			vx = -Math.abs(vx);
		}
		if (y - vy < 0) {
			vy = Math.abs(vy);
		} else if (y + vy > canvas.getHeight() - getBitmapHeight()) {
			vy = -Math.abs(vy);
		}
	}

	public int getBitmapWidth() {
		if (bitmap != null) {
			return bitmap.getWidth();
		} else {
			return 0;
		}
	}

	public int getBitmapHeight() {
		if (bitmap != null) {
			return bitmap.getHeight();
		} else {
			return 0;
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceCreated(SurfaceHolder holder) {
		ballThread.setRunnable(true);
		ballThread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		ballThread.setRunnable(false);

		while (retry) {
			try {
				ballThread.join();
				retry = false;
			} catch (InterruptedException ie) {
			}

			break;
		}

		ballThread = null;

	}

}
