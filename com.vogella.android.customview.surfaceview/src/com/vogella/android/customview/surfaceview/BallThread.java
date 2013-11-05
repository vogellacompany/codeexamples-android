package com.vogella.android.customview.surfaceview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class BallThread extends Thread {

	private SurfaceHolder sh;
	private Ball ball;

	private Canvas canvas;

	private boolean run = false;

	public BallThread(SurfaceHolder holder, Ball ball) {
		sh = holder;
		this.ball = ball;
	}

	public void setRunnable(boolean run) {
		this.run = run;
	}

	@Override
	public void run() {
		while (run) {
			canvas = null;
			try {
				canvas = sh.lockCanvas(null);
				synchronized (sh) {
					ball.onDraw(canvas);
				}

			} finally {
				if (canvas != null) {
					sh.unlockCanvasAndPost(canvas);
				}
			}

		}
	}

	public Canvas getCanvas() {
		if (canvas != null) {
			return canvas;
		} else {
			return null;
		}
	}
}