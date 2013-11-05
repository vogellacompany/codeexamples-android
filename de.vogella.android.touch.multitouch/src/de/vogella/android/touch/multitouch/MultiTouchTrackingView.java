package de.vogella.android.touch.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.View;

class MultiTouchTrackingView extends View {

	private final MultitouchActivity multitouchActivity;

	public MultiTouchTrackingView(MultitouchActivity multitouchActivity,
			Context context) {
		super(context);
		this.multitouchActivity = multitouchActivity;
		setBackgroundColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < MultitouchActivity.MAX_POINTERS; i++) {
			PointF point = this.multitouchActivity.points[i];
			if (point != null) {
				this.multitouchActivity.paint.setColor(this.multitouchActivity
						.getColor(i));
				canvas.drawCircle(point.x, point.y,
						this.multitouchActivity.radius,
						this.multitouchActivity.paint);
				String text = this.multitouchActivity.getActionText(i);
				float textWidth = this.multitouchActivity.paint
						.measureText(text);
				canvas.drawText(text, point.x - textWidth / 2, point.y
						- this.multitouchActivity.radius
						- this.multitouchActivity.calcDevicePixels(8),
						this.multitouchActivity.paint);
			}
		}
		canvas.drawText("Last pointer count: "
				+ this.multitouchActivity.pointerCount, 10,
				this.multitouchActivity.calcDevicePixels(30),
				this.multitouchActivity.paintInfoText);
	}
}