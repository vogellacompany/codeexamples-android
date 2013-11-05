package de.vogella.android.ownview;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyDrawView extends View {

	private ArrayList<Point> points = new ArrayList<Point>();
	private Paint paint;
	private Path path;

	public MyDrawView(Context context) {
		super(context);
		paint = new Paint();
		path = new Path();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(20f);
	}

	public MyDrawView(Context context, AttributeSet attrs) {
		this(context);
	}

	public MyDrawView(Context context, AttributeSet attrs, int defStyle) {
		this(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
		Log.e("Lars", "draw");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			points.add(new Point(x, y));
			invalidate();
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Parcelable onSaveInstanceState = super.onSaveInstanceState();
		Bundle bundle = new Bundle();
		bundle.putParcelable("instanceState", super.onSaveInstanceState());
		bundle.putSerializable("stateToSave", points);

		return bundle;

	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			System.out.println("HELLLP");
			Bundle bundle = (Bundle) state;
			this.points = (ArrayList<Point>) bundle
					.getSerializable("stateToSave");
			for (Point point : points) {
				path.lineTo(point.x, point.y);
			}
			super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
			return;
		}

		super.onRestoreInstanceState(state);

	}

	private static class Point implements Serializable {

		public Point(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public float x;
		public float y;
	}
}
