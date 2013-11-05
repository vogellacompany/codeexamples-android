package de.vogella.android.viewscreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class ViewScreenshotActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// saveFile(result);
	}

	public void onClick(View view) {
		Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ALPHA_8);
		View from = findViewById(R.id.button1y);
		Bitmap result = drawViewToBitmap(from, bitmap);
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setImageBitmap(result);
	}

	public void saveFile(Bitmap bitmap) {
		String path = Environment.getExternalStorageDirectory().toString();
		OutputStream fOut = null;
		File file = new File(path, "screenshot" + ".png");
		try {
			fOut = new FileOutputStream(file);

			bitmap.compress(Bitmap.CompressFormat.PNG, 90, fOut);
			fOut.flush();
			fOut.close();

			MediaStore.Images.Media.insertImage(getContentResolver(),
					file.getAbsolutePath(), file.getName(), file.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Bitmap drawViewToBitmap(View view, Bitmap bitmap) {

		bitmap.eraseColor(Color.TRANSPARENT);

		int bitmapWidth = bitmap.getWidth();

		int bitmapHeight = bitmap.getHeight();

		Canvas canvas = new Canvas(bitmap);

		int measureWidth = View.MeasureSpec.makeMeasureSpec(bitmapWidth,
				View.MeasureSpec.EXACTLY);

		int measuredHeight = View.MeasureSpec.makeMeasureSpec(bitmapHeight,
				View.MeasureSpec.EXACTLY);

		view.measure(measureWidth, measuredHeight);

		view.layout(0, 0, bitmapWidth, bitmapHeight);

		view.draw(canvas);

		return bitmap;

	}
}