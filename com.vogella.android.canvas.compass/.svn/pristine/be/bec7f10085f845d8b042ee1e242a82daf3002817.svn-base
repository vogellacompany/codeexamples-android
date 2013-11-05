package com.paad.compass;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;

public class Compass extends Activity {
    
	float pitch = 25;
	float roll = 60;
	float heading = 90;

	//CompassView compassView;
	CompassSurfaceView compassView;
	SensorManager sensorManager;
	
	@Override
	public void onCreate(Bundle icicle) {
	  super.onCreate(icicle); 
	  //requestWindowFeature(8);
	  
	  setContentView(R.layout.main);

	  compassView = (CompassSurfaceView)this.findViewById(R.id.compassView);
	  //compassView = (CompassView)this.findViewById(R.id.compassView);
    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	  updateOrientation(heading, pitch, roll);
	}

	private final SensorListener sensorListener = new SensorListener() {

	  public void onSensorChanged(int sensor, float[] values) {
	    updateOrientation(values[SensorManager.DATA_Z], 
	                      values[SensorManager.DATA_Y], 
	                      values[SensorManager.DATA_X]);
	  }

      public void onAccuracyChanged(int sensor, int accuracy) {}

	};

    
    private void updateOrientation(float _roll, float _pitch, float _heading) {
    	  heading = _heading;
    	  pitch = _pitch;
    	  roll = _roll;

    	  if (compassView!= null) {
    		compassView.setBearing(heading);
    	    compassView.setPitch(pitch);
    	    compassView.setRoll(roll);
    	    //compassView.invalidate();
    	  }
    	}

    @Override
    protected void onResume()
    {
      super.onResume();
      sensorManager.registerListener(sensorListener, 
    		  SensorManager.SENSOR_ORIENTATION,
              SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onStop()
    {
      sensorManager.unregisterListener(sensorListener);
      super.onStop();
    }

    
}