package com.vogella.android.animation.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Random;

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
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createAnimation();
			}
		});
		DisplayMetrics displaymetrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		width = displaymetrics.widthPixels;
		height = displaymetrics.heightPixels;
		randon = new Random();
		createAnimation();


	}

	public void onClick(View view) {
		String string = button.getText().toString();
		int hitTarget = Integer.valueOf(string) + 1;
		button.setText(String.valueOf(hitTarget));
	}


	private void createAnimation() {
		set = buildAnimation();
		set.start();
		set.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				int nextX = randon.nextInt(width);
				int nextY = randon.nextInt(height);
				animation1 = ObjectAnimator.ofFloat(button, "x", button.getX(),nextX);
				animation1.setDuration(1400);
				animation2 = ObjectAnimator.ofFloat(button, "y", button.getY(), nextY);
				animation2.setDuration(1400);
				set.playTogether(animation1, animation2);
				set.start();
			}
		});

	}

	private AnimatorSet buildAnimation(){
		int nextX = randon.nextInt(width);
		int nextY = randon.nextInt(height);

		animation1 = ObjectAnimator.ofFloat(button, "x", nextX);
		animation1.setDuration(1400);
		animation2 = ObjectAnimator.ofFloat(button, "y", nextY);
		animation2.setDuration(1400);
		AnimatorSet set = new AnimatorSet();
		set.playTogether(animation1, animation2);
		return set;
	}
}
