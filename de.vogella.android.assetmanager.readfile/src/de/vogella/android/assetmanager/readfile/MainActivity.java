package de.vogella.android.assetmanager.readfile;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		AssetManager manager = getAssets();
		try {
			InputStream open = manager.open("logo.png");
			Bitmap bitmap = BitmapFactory.decodeStream(open);
			ImageView view = (ImageView) findViewById(R.id.imageView1);
			view.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}