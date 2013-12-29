package com.vogella.android.customview.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	private Paint paint;
	private int color;

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup();
	}

	private void setup() {
		color = getResources().getColor(R.color.paper);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK);
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(color);
		canvas.drawLine(0, 0, getMeasuredHeight(), getMeasuredWidth(), paint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(),
				getMeasuredHeight(), paint);
		canvas.drawLine(50, 0, 50, getMeasuredHeight(), paint);
		canvas.save();
		canvas.translate(60, 0);
		super.onDraw(canvas);
		canvas.restore();
	}
}
