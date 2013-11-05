package com.paad.compass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.Paint.Align;
import android.graphics.Path.Direction;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.*;

public class CompassSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
  private SurfaceHolder holder;
  private MySurfaceViewThread mySurfaceViewThread;
  private boolean hasSurface;
  
  public CompassSurfaceView(Context context) {
    super(context);
    initCompassView();
    init();
  }   

  public CompassSurfaceView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initCompassView();
    init();
  }

  public CompassSurfaceView(Context context, AttributeSet attrs, int defaultStyle) {
    super(context, attrs, defaultStyle);
    initCompassView();
    init();
  }
  
  private void init() {
    // Create a new SurfaceHolder and assign this class as its callback.
    holder = getHolder();
    holder.addCallback(this);
    hasSurface = false;
  }
  
  public void resume() {
    // Create and start the graphics update thread.
    if (mySurfaceViewThread == null) {
      mySurfaceViewThread = new MySurfaceViewThread();
    if (hasSurface == true)
      mySurfaceViewThread.start();
    }
  }
  
  public void pause() {
    // Kill the graphics update thread
    if (mySurfaceViewThread != null) {
      mySurfaceViewThread.requestExitAndWait();
      mySurfaceViewThread = null;
    }
  }
  
  public void surfaceCreated(SurfaceHolder holder) {
    hasSurface = true;
    resume();
//    if (mySurfaceViewThread != null)
//      mySurfaceViewThread.start();
  }

  public void surfaceDestroyed(SurfaceHolder holder) {
    hasSurface = false;
    pause();
  }
  
  public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    if (mySurfaceViewThread != null)
      mySurfaceViewThread.onWindowResize(w, h);
  }
  
  class MySurfaceViewThread extends Thread {
    private boolean done;
 
    private long lastUpdate;
    
    MySurfaceViewThread() {
      super();
      lastUpdate = System.currentTimeMillis();
      done = false;
    }
    
    @Override
    public void run() {
      SurfaceHolder surfaceHolder = holder;
      // Repeat the drawing loop until the thread is stopped.
      while (!done) {
//        long time = System.currentTimeMillis();
//        long dif = time-lastUpdate;
//        lastUpdate = time;
        
        // Lock the surface and return the canvas to draw onto.
        Canvas canvas = surfaceHolder.lockCanvas();

        if (rollText != null && skyPath != null && outerRingPath != null && groundPaint != null && backgroundBitmap != null && rollArrowPicture != null && pitchScalePicture != null) {
          // Draw the outer ring and inner sky backgrounds.
          canvas.drawBitmap(backgroundBitmap, 0, 0, null);    
          
          canvas.save();          
          canvas.rotate(-roll, px, py);
        
          // Draw the ground and the outline for it.
          canvas.drawPath(skyPath, skyPaint);
                  
          // Draw the arrow
          rollArrowPicture.draw(canvas);

          // Draw the string
//          if (dif > 0)
//          {
//            long fps = (1000l/dif);
//            rollText = String.valueOf(fps);
//          }
//          rollText = String.valueOf(pitch);
                 
          canvas.drawText(rollText,
                          rollTextLeft,
                          rollTextTop,
                          textPaint);
          // Pitch Scale
          pitchScalePicture.draw(canvas);
          
          canvas.restore();
        
          canvas.save();
          //  Compass Markings
          canvas.rotate(-1*(bearing), px, py);
          canvas.drawBitmap(compassMarkingsBitmap, 0, 0, bitmapPaint);
          canvas.restore();
          
          canvas.drawBitmap(glassTopBitmap, 0, 0, null);        
        }
        // Unlock the canvas and render the current image.
        surfaceHolder.unlockCanvasAndPost(canvas);
      }
    }

    public void requestExitAndWait() {
      // Mark this thread as complete and combine into
      // the main application thread.
      done = true;
      try {
        join();
      } catch (InterruptedException ex) { }
    }
    
    public void onWindowResize(int w, int h) {
      float ringWidth = textHeight + 4;
      
      int height = h;
      int width = w;
    
      px = width/2;
      py = height/2;
      center = new Point(px, py);
    
      radius = Math.min(px, py)-2;
    
      boundingBox = new RectF(center.x - radius, 
                                    center.y - radius, 
                                    center.x + radius, 
                                    center.y + radius);
    
      innerBoundingBox = new RectF(center.x - radius + ringWidth, 
                                         center.y - radius + ringWidth, 
                                         center.x + radius - ringWidth, 
                                         center.y + radius - ringWidth);
    
      innerRadius = innerBoundingBox.height()/2;
    
      borderGradient = new RadialGradient(px, py, radius,
                borderGradientColors, 
                borderGradientPositions,
                TileMode.CLAMP);
      
      pgb = new Paint();
      pgb.setShader(borderGradient);
    
      outerRingPath = new Path();
      outerRingPath.addOval(boundingBox, Direction.CW);
        
      skyShader = new LinearGradient(center.x, 
                innerBoundingBox.top, 
                center.x, 
                innerBoundingBox.bottom, 
                skyHorizonColorFrom, 
                skyHorizonColorTo, 
                TileMode.CLAMP);
      skyPaint = new Paint();
      skyPaint.setShader(skyShader);
    
      groundShader = new LinearGradient(center.x, 
                   innerBoundingBox.top,
                   center.x,
                   innerBoundingBox.bottom, 
                   groundHorizonColorFrom,
                   groundHorizonColorTo,
                   TileMode.CLAMP);
      groundPaint = new Paint();
      groundPaint.setShader(groundShader);
            
      markWidth = radius/3;
      startX = center.x-markWidth;
      endX = center.x+markWidth;
    
      pxPerDegree = (innerBoundingBox.height()/2)/45f;

      glassShader = new RadialGradient(px, py, 
          (int)innerRadius,
          glassGradientColors,
          glassGradientPositions,
          TileMode.CLAMP);
      glassPaint = new Paint();
      glassPaint.setShader(glassShader);
  
      glassTopBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      glassTopBitmap.eraseColor(Color.TRANSPARENT);
      Canvas c = new Canvas(glassTopBitmap);
      c.drawOval(innerBoundingBox, glassPaint);      
      // Draw the outer ring
      c.drawOval(boundingBox, circlePaint);    
      // Draw the inner ring
      circlePaint.setStrokeWidth(2);
      c.drawOval(innerBoundingBox, circlePaint);
      
      rollTextTop = innerBoundingBox.top + textHeight + 22;
      
      // Background
      backgroundBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
      Canvas c1 = new Canvas(backgroundBitmap);
      c1.drawPath(outerRingPath, pgb);
      c1.drawOval(innerBoundingBox, groundPaint);
      c1.rotate(180, center.x, center.y);
      for (int i = -180; i < 180; i += 10)
      {
        // Show a numeric value every 30 degrees
        if (i % 30 == 0) {
          String rollString = String.valueOf(i*-1);
          float rollStringWidth = textPaint.measureText(rollString);
          PointF rollStringCenter = new PointF(center.x-rollStringWidth / 2,
                    innerBoundingBox.top+1+textHeight);
    
          c1.drawText(rollString, 
                          rollStringCenter.x, rollStringCenter.y, 
                          textPaint);
        }
        // Otherwise draw a marker line
        else {
          c1.drawLine(center.x, (int)innerBoundingBox.top, 
                          center.x, (int)innerBoundingBox.top + 5, 
                          markerPaint);
        }
    
        c1.rotate(10, center.x, center.y);
      }
      
      // Roll Scale  
      //pitchScaleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      //Canvas pitchScale = new Canvas(pitchScaleBitmap);

      pitchScalePicture = new Picture();
      Canvas pitchScale = pitchScalePicture.beginRecording(width, height);

      for (int i = 90; i >= -90; i -= 10)
      {
        double ypos = center.y + i*pxPerDegree; //center.y
  
        // Only display the scale within the inner face.
        if ((ypos < (innerBoundingBox.top + 2*textHeight)) || 
            (ypos > innerBoundingBox.bottom - 2*textHeight))
          continue;
    
        // Draw a line and the tilt angle for each scale increment.
        pitchScale.drawLine(startX, (float)ypos, endX, (float)ypos, markerPaint);
        int displayPos = (int)(0 - i); // pitch
        String displayString = String.valueOf(displayPos*2);
        float stringSizeWidth = textPaint.measureText(displayString);
        pitchScale.drawText(displayString, 
                        (int)(center.x-stringSizeWidth/2), 
                        (int)(ypos)-2, 
                        textPaint);
      }
      pitchScalePicture.endRecording();

      // Compass Markings
      //compassMarkingsPicture = new Picture();
      //Canvas compassCanvas = compassMarkingsPicture.beginRecording(width, height);

      compassMarkingsBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);      
      Canvas compassCanvas = new Canvas(compassMarkingsBitmap);
      
      String headString = ">N<";
      
      float headStringWidth = textPaint.measureText(headString);
      PointF headStringCenter = new PointF(center.x - headStringWidth / 2,
                                           boundingBox.top + 1 + textHeight);
  
      textPaint.setColor(Color.GREEN);
      compassCanvas.drawText(headString, 
                             headStringCenter.x, headStringCenter.y, 
                             textPaint);
      textPaint.setColor(Color.WHITE);
      
      double increment = 22.5;
      compassCanvas.rotate((int)increment, center.x, center.y);            
      for (double i = increment; i < 360; i += increment)
      {
        CompassDirection cd = CompassDirection.values()[(int)(i / 22.5)];
        headString = cd.toString();
    
        headStringWidth = textPaint.measureText(headString);
        headStringCenter = new PointF(center.x - headStringWidth / 2,
                                      boundingBox.top + 1 + textHeight);
    
        compassCanvas.drawText(headString, 
                               headStringCenter.x, headStringCenter.y, 
                               textPaint);
    
        compassCanvas.rotate((int)increment, center.x, center.y);
      }
      //compassMarkingsPicture.endRecording();
      
      // Roll Arrow
      rollArrowPicture = new Picture();
      Canvas rollArrowBitmapCanvas = rollArrowPicture.beginRecording(width, height);
      Path rollArrow = new Path();
      rollArrow.moveTo(center.x - 5, (int)innerBoundingBox.top + 22);
      rollArrow.lineTo(center.x, (int)innerBoundingBox.top + 14);
      rollArrow.moveTo(center.x + 5, innerBoundingBox.top + 22);
      rollArrow.lineTo(center.x, innerBoundingBox.top + 14);
      markerPaint.setColor(Color.WHITE);
      rollArrowBitmapCanvas.drawPath(rollArrow, markerPaint);
      rollArrowPicture.endRecording();
    }    
  }
  
  Picture rollArrowPicture;
  Picture pitchScalePicture;
  Picture compassMarkingsPicture;
  Bitmap pitchScaleBitmap;
  Bitmap backgroundBitmap;
  Bitmap compassMarkingsBitmap;
  Bitmap glassTopBitmap;
  Canvas skyCanvas;

  Paint glassPaint;
  RadialGradient glassShader;
  Path skyPath;
  int markWidth;
  int startX;
  int endX;
  float pxPerDegree;
  
  private Paint markerPaint;
	private Paint textPaint;
	private Paint circlePaint;
	private int textHeight;
	
	int[] borderGradientColors;
	float[] borderGradientPositions;

	int[] glassGradientColors;
	float[] glassGradientPositions;

	int skyHorizonColorFrom;
	int skyHorizonColorTo;
	int groundHorizonColorFrom;
	int groundHorizonColorTo;
	
  Point center;
  int radius;
  RectF boundingBox;
  public RectF innerBoundingBox;
  float innerRadius;
  RadialGradient borderGradient;
  LinearGradient skyShader;
  LinearGradient groundShader;
  Path outerRingPath;
  Paint pgb;
  Paint skyPaint;
  Paint groundPaint;
  Paint bitmapPaint;
  int px;
  int py;
  double justTiltY;
  String rollText;
  double rollTextWidth;
  float rollTextLeft;
  float rollTextTop;
  
  private float bearing;  
  
  private enum CompassDirection { N, NNE, NE, ENE,
      E, ESE, SE, SSE,
      S, SSW, SW, WSW,
      W, WNW, NW, NNW }

    
  public void setBearing(float _bearing) {
    bearing = _bearing;
  }
  public float getBearing() {
    return bearing;
  }
	
  float pitch = 10;
  float roll = 45;  
    
  public float getPitch() {
    return pitch;
  }
  public void setPitch(float pitch) {    
    this.pitch = pitch;
          
    float tiltDegree = pitch;
    
    while (tiltDegree > 90 || tiltDegree < -90)
    {
      if (tiltDegree > 90) tiltDegree = -90 + (tiltDegree - 90);
        if (tiltDegree < -90) tiltDegree = 90 - (tiltDegree + 90);
    }  
    
    if (skyPath != null && innerBoundingBox != null) {
      skyPath.reset();
      skyPath.addArc(innerBoundingBox, 
                     -tiltDegree,
                     (180 + (2 * tiltDegree)));
//      
//      double h = innerRadius*Math.cos(Math.toRadians(90-pitch));
//      justTiltY = center.y - h;
    }
  }

  public float getRoll() {
    return roll;
  }
  public void setRoll(float roll) {    
    float rollDegree = roll;
    while (rollDegree > 180 || rollDegree < -180)
    {
      if (rollDegree > 180) rollDegree = -180 + (rollDegree - 180);
       if (rollDegree < -180) rollDegree = 180 - (rollDegree + 180);
    }
    this.roll = rollDegree;
    
    if (innerBoundingBox != null) {
      rollText = String.valueOf(roll);
      rollTextWidth = textPaint.measureText(rollText);
      rollTextLeft = (float)(center.x - rollTextWidth / 2); 
    }
  }

  protected void initCompassView() {
    setFocusable(true);
    Resources r = this.getResources();

    bitmapPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
    
    circlePaint = new Paint();
    circlePaint.setAntiAlias(true);
    circlePaint.setColor(R.color.background_color);
    circlePaint.setStrokeWidth(1);
    circlePaint.setStyle(Paint.Style.STROKE);

    textPaint = new Paint();
    textPaint.setAntiAlias(true);
    textPaint.setLinearText(true);
    textPaint.setColor(r.getColor(R.color.text_color));
    textPaint.setFakeBoldText(true);
    textPaint.setTextAlign(Align.LEFT);

    textHeight = (int)textPaint.measureText("yY");

    markerPaint = new Paint();
    markerPaint.setAntiAlias(true);
    markerPaint.setColor(r.getColor(R.color.marker_color));
    markerPaint.setStrokeWidth(1);
    markerPaint.setStyle(Paint.Style.STROKE);

    borderGradientColors = new int[4];
    borderGradientPositions = new float[4];

    borderGradientColors[3] = r.getColor(R.color.outer_border);
    borderGradientColors[2] = r.getColor(R.color.inner_border_one);
    borderGradientColors[1] = r.getColor(R.color.inner_border_two);
    borderGradientColors[0] = r.getColor(R.color.inner_border);
    borderGradientPositions[3] = 0.0f;
    borderGradientPositions[2] = 1-0.03f;
    borderGradientPositions[1] = 1-0.06f;
    borderGradientPositions[0] = 1.0f;

    glassGradientColors = new int[5];
    glassGradientPositions = new float[5];

    int glassColor = 245;
    glassGradientColors[4] = Color.argb(65, glassColor, glassColor, glassColor);
    glassGradientColors[3] = Color.argb(100, glassColor, glassColor, glassColor);
    glassGradientColors[2] = Color.argb(50, glassColor, glassColor, glassColor);
    glassGradientColors[1] = Color.argb(0, glassColor, glassColor, glassColor);
    glassGradientColors[0] = Color.argb(0, glassColor, glassColor, glassColor);
    glassGradientPositions[4] = 1-0.0f;
    glassGradientPositions[3] = 1-0.06f;
    glassGradientPositions[2] = 1-0.10f;
    glassGradientPositions[1] = 1-0.20f;
    glassGradientPositions[0] = 1-1.0f;

    skyHorizonColorFrom = r.getColor(R.color.horizon_sky_from);
    skyHorizonColorTo = r.getColor(R.color.horizon_sky_to);

    groundHorizonColorFrom = r.getColor(R.color.horizon_ground_from);
    groundHorizonColorTo = r.getColor(R.color.horizon_ground_to);

    skyPath = new Path();
  }
  
  @Override    
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 
    // The compass is a circle that fills as much space as possible.
    // Set the measured dimensions by figuring out the shortest boundary,
    // height or width.
    int measuredWidth = measure(widthMeasureSpec);
    int measuredHeight = measure(heightMeasureSpec);
        
    int d = Math.min(measuredWidth, measuredHeight);
        
    setMeasuredDimension(d, d);    
  }
      
  private int measure(int measureSpec) {
    int result = 0; 

    // Decode the measurement specifications.
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec); 

    if (specMode == MeasureSpec.UNSPECIFIED) {
      // Return a default size of 200 if no bounds are specified. 
      result = 200;
    } else {
      // As you want to fill the available space
      // always return the full available bounds.
      result = specSize;
    } 
    return result;
  }
}