package com.vogella.android.drawables.custom;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView button = (ImageView) findViewById(R.id.image);

		InputStream resource = getResources().openRawResource(R.drawable.eye);

		Bitmap bitmap = BitmapFactory.decodeStream(resource);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		Bitmap b = Bitmap.createScaledBitmap(bitmap, width, height, false);

		button.setBackground(new MyRoundCornerDrawable(b));
	}
}
