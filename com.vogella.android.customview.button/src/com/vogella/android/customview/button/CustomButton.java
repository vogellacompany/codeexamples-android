package com.vogella.android.customview.button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;

public class CustomButton extends View {

	private Paint fillPaint;
	private Paint paint;

	public CustomButton(Context context) {
		super(context);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLACK);
		paint.setTextSize(40);

		fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fillPaint.setStyle(Style.FILL);
		fillPaint.setColor(Color.RED);
		CornerPathEffect effect = new CornerPathEffect(30);
		fillPaint.setPathEffect(effect);
		fillPaint.setStyle(Style.FILL_AND_STROKE);
		fillPaint.setShader(new LinearGradient(0F, 120, getWidth(),
				getHeight(), Color.CYAN, Color.RED, Shader.TileMode.CLAMP));

	}

	@Override
	protected void onDraw(Canvas canvas) {
		Rect rect = new Rect(0, 0, getWidth() / 2, getHeight() / 4);
		canvas.drawRect(rect, fillPaint);
		canvas.drawText("Update", 10, 80, paint);
	}
}
