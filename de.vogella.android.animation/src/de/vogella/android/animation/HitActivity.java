package de.vogella.android.animation;

import java.util.Random;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HitActivity extends Activity {
	private ObjectAnimator animation1;
	private ObjectAnimator animation2;
	private Button button;
	private Random randon;
	private int width;
	private int height;
	private AnimatorSet set;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.target);
		width = getWindowManager().getDefaultDisplay().getWidth();
		height = getWindowManager().getDefaultDisplay().getHeight();
		randon = new Random();

		set = createAnimation();
		set.start();
		set.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				int nextX = randon.nextInt(width);
				int nextY = randon.nextInt(height);
				animation1 = ObjectAnimator.ofFloat(button, "x", button.getX(),
						nextX);
				animation1.setDuration(1400);
				animation2 = ObjectAnimator.ofFloat(button, "y", button.getY(),
						nextY);
				animation2.setDuration(1400);
				set.playTogether(animation1, animation2);
				set.start();
			}
		});
	}

	public void onClick(View view) {
		String string = button.getText().toString();
		int hitTarget = Integer.valueOf(string) + 1;
		button.setText(String.valueOf(hitTarget));
	}

	private AnimatorSet createAnimation() {
		int nextX = randon.nextInt(width);
		int nextY = randon.nextInt(height);
		button = (Button) findViewById(R.id.button1);
		animation1 = ObjectAnimator.ofFloat(button, "x", nextX);
		animation1.setDuration(1400);
		animation2 = ObjectAnimator.ofFloat(button, "y", nextY);
		animation2.setDuration(1400);
		AnimatorSet set = new AnimatorSet();
		set.playTogether(animation1, animation2);
		return set;
	}
}
