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

//    public void setColor(int r, int g, int b) {
//        int rgb = Color.rgb(r, g, b);
//        paint.setColor(rgb);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(255, 0, 0, 255);
        canvas.drawLine(0f, 0f, 200f, 200f, paint);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            //
            path.moveTo(x,y);
        }

        if (action == MotionEvent.ACTION_MOVE) {
            //
            path.lineTo(x,y);
        }
        invalidate();
        return true;
    }
} 