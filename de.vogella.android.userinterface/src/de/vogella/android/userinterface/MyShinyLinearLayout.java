package de.vogella.android.userinterface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MyShinyLinearLayout extends LinearLayout {
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Path path;

	public MyShinyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(R.drawable.background);
	}

	private void createShineArea() {
		int width = getWidth();
		int height = (int) (0.9 * getHeight());
		path = new Path();
		path.moveTo(0, 0);
		path.lineTo(width, 0);
		path.lineTo(width, height);
		path.close();
		paint.setShader(new LinearGradient(0, 0, 0, height, 0x55ffffff,
				0x10ffffff, Shader.TileMode.CLAMP));
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		if (path == null) {
			createShineArea();
		}
		// Draw the shine behind the children.
		canvas.drawPath(path, paint);
		// Draw the children
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// Invalidate the path whenever the size changes.
		path = null;
	}

}
