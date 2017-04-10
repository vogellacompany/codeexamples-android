package com.vogella.android.touch.single;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;


public class SingleTouchActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_single_touch, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		ColorPickerFragment colorPickerFragment = new ColorPickerFragment();
		colorPickerFragment.show(getFragmentManager(), "colorpicker");
		return true;
	}



    public static class ColorPickerFragment extends DialogFragment {
		SeekBar red;
		SeekBar green;
		SeekBar blue;
		private SingleTouchActivity listener;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_colorpicker, container, false);
            final View preview = view.findViewById(R.id.preview);
            SeekBar.OnSeekBarChangeListener listener =  new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int argb = Color.argb(255, red.getProgress(), green.getProgress(), blue.getProgress());
                    preview.setBackgroundColor(argb);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
            red = (SeekBar) view.findViewById(R.id.red);
            red.setOnSeekBarChangeListener(listener);
			green = (SeekBar) view.findViewById(R.id.green);
            green.setOnSeekBarChangeListener(listener);
            blue = (SeekBar) view.findViewById(R.id.blue);
            blue.setOnSeekBarChangeListener(listener);
            getDialog().setTitle("Pick a color");
            return view;
		}


		@Override
		public void onAttach(Context context) {
			super.onAttach(context);
			if (context instanceof SingleTouchActivity ) {
				listener = (SingleTouchActivity) context;
			}
		}

		@Override
		public void onStop() {
			listener.setColor(red.getProgress(), green.getProgress(),blue.getProgress());
			super.onStop();
		}
	}

    public void setColor(int r, int g, int b) {
        TouchEventView drawingView = (TouchEventView) findViewById(R.id.drawingview);
        drawingView.setColor(r, g, b);
    }



}
