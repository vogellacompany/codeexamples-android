package de.vogella.android.touch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class WriteOnScreenActivity extends Activity {
	Bitmap bitmap;
	private TouchEventViewWithImage view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new TouchEventViewWithImage(this, null);
		setContentView(view);
		System.out.println("This is a log message");
		Log.e("Mein Tag", "Hello");
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return true;
	};

	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.pickimage:
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			startActivityForResult(intent, 10);
			break;

		default:
			break;
		}
		return false;
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		InputStream stream = null;
		if (resultCode == RESULT_OK && requestCode == 10) {
			if (bitmap != null) {
				view.setBitmap(null);
				bitmap.recycle();
			}

			try {
				stream = getContentResolver().openInputStream(data.getData());
				bitmap = BitmapFactory.decodeStream(stream);
				view.setBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		}
	};
}