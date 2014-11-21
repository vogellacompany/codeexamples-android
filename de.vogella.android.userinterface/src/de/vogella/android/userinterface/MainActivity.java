package de.vogella.android.userinterface;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageButton refreshButton;
	private Handler handler = new Handler();

	/** Called when the activity is first created. */
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main5);
		refreshButton = (ImageButton) findViewById(R.id.refresh);
		refreshButton
				.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}

	public void onClick(View view) {
		final RoundImageView photo = (RoundImageView)  findViewById(R.id.editablePhoto1);
		final View progress = findViewById(R.id.progressBar1);
		progress.setVisibility(View.VISIBLE);
		refreshButton.setVisibility(View.GONE);
		Runnable runnable = new Runnable() {

			private Bitmap decodeStream;

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				decodeStream = null;
				try {
					if (photo != null
							&& photo instanceof RoundImageView) {
						decodeStream = BitmapFactory
								.decodeStream(getAssets().open(
										"lars.png"));
					}

					
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.post(new Runnable() {
					@Override
					public void run() {
						photo.setImageBitmap(decodeStream);
						progress.setVisibility(View.GONE);
						refreshButton.setVisibility(View.VISIBLE);
					}
				});

			}
		};

		new Thread(runnable).start();

		Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();

	}
}