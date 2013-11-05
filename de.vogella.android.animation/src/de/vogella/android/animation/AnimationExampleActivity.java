package de.vogella.android.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationExampleActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void startAnimation(View view) {
		float dest = 0;
		final ImageView aniView = (ImageView) findViewById(R.id.imageView1);

		switch (view.getId()) {
		case R.id.Button01:

			dest = 360;
			if (aniView.getRotation() == 360) {
				dest = 0;
			}
			// ObjectAnimator animation1 = ObjectAnimator.ofFloat(aniView,
			// "rotation", dest);
			// animation1.setDuration(2000);
			// animation1.start();

			aniView.animate().rotation(dest).setDuration(1000).scaleX(2)
					.scaleY(2).withEndAction(new Runnable() {

						@Override
						public void run() {
							//
							aniView.animate()
									.rotationXBy(100)
									.rotation(
											Math.abs(360 - aniView
													.getRotation()))
									.scaleX(0.4F).scaleY(0.4F)
									.setDuration(1000);
						}
					});

			// Show how to load an animation from XML
			// Animation animation1 = AnimationUtils.loadAnimation(this,
			// R.anim.myanimation);
			// animation1.setAnimationListener(this);
			// animatedView1.startAnimation(animation1);
			break;

		case R.id.Button02:
			// Shows how to define a animation via code
			// Also use an Interpolator (BounceInterpolator)
			Paint paint = new Paint();
			TextView aniTextView = (TextView) findViewById(R.id.textView1);
			float measureTextCenter = paint.measureText(aniTextView.getText()
					.toString());
			dest = 0 - measureTextCenter;
			if (aniTextView.getX() < 0) {
				dest = 0;
			}
			ObjectAnimator animation2 = ObjectAnimator.ofFloat(aniTextView,
					"x", dest);
			animation2.setDuration(2000);
			animation2.start();
			break;

		case R.id.Button03:
			// Demonstrate fading and adding an AnimationListener

			dest = 1;
			if (aniView.getAlpha() > 0) {
				dest = 0;
			}
			ObjectAnimator animation3 = ObjectAnimator.ofFloat(aniView,
					"alpha", dest);
			animation3.setDuration(2000);
			animation3.start();
			break;

		case R.id.Button04:

			ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha",
					0f);
			fadeOut.setDuration(2000);
			ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,
					"translationX", -500f, 0f);
			mover.setDuration(2000);
			ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha",
					0f, 1f);
			fadeIn.setDuration(2000);
			AnimatorSet animatorSet = new AnimatorSet();

			animatorSet.play(mover).with(fadeIn).after(fadeOut);
			animatorSet.start();
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mymenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(this, HitActivity.class);
		startActivity(intent);
		return true;
	}
}