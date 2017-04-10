package com.vogella.android.touch.single;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();


    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setColor(int r, int g, int b) {
        int rgb = Color.rgb(r, g, b);
        paint.setColor(rgb);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(122, 255, 0, 0);
        canvas.drawCircle(100, 100, 40, paint);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            path.moveTo(x, y);
        }

        if (action == MotionEvent.ACTION_MOVE) {
            path.lineTo(x, y);
        }
        invalidate();

        return true;
    }
} 