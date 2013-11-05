package com.vogella.android.canvas.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class MatrixView extends View {

	public MatrixView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Bitmap targetBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		Matrix matrix = new Matrix();
		matrix.setRotate(50, targetBitmap.getWidth() / 2,
				targetBitmap.getHeight() / 2);
		matrix.setSkew(2, 2);
		canvas.drawBitmap(targetBitmap, matrix, new Paint());
		super.onDraw(canvas);
	}

}
