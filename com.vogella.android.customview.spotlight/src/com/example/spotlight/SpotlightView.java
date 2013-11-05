package com.example.spotlight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class SpotlightView extends View {
	private int mTargetId;

	private Bitmap mMask;
	private float mMaskX;
	private float mMaskY;
	private float mMaskScale;
	private Matrix mShaderMatrix = new Matrix();

	private Bitmap mTargetBitmap;
	private final Paint mPaint = new Paint();

	private AnimationSetupCallback mCallback;

	public interface AnimationSetupCallback {
		void onSetupAnimation(SpotlightView spotlight);
	}

	public SpotlightView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.SpotlightView, 0, 0);
		try {
			mTargetId = a.getResourceId(R.styleable.SpotlightView_target, 0);

			int maskId = a.getResourceId(R.styleable.SpotlightView_mask, 0);
			mMask = convertToAlphaMask(BitmapFactory.decodeResource(
					getResources(), maskId));

			android.util.Log.d("Spotlight", "c=" + mMask.getConfig());
		} catch (Exception e) {
			android.util.Log
					.e("Spotlight", "Error while creating the view:", e);
		} finally {
			a.recycle();
		}
	}

	public float getMaskScale() {
		return mMaskScale;
	}

	public void setMaskScale(float maskScale) {
		mMaskScale = maskScale;
		invalidate();
	}

	public float getMaskX() {
		return mMaskX;
	}

	public void setMaskX(float maskX) {
		mMaskX = maskX;
		invalidate();
	}

	public float getMaskY() {
		return mMaskY;
	}

	public void setMaskY(float maskY) {
		mMaskY = maskY;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float maskW = mMask.getWidth() / 2.0f;
		float maskH = mMask.getHeight() / 2.0f;

		float x = mMaskX - maskW * mMaskScale;
		float y = mMaskY - maskH * mMaskScale;

		mShaderMatrix.setScale(1.0f / mMaskScale, 1.0f / mMaskScale);
		mShaderMatrix.preTranslate(-x, -y);

		mPaint.getShader().setLocalMatrix(mShaderMatrix);

		canvas.translate(x, y);
		canvas.scale(mMaskScale, mMaskScale);
		canvas.drawBitmap(mMask, 0.0f, 0.0f, mPaint);
	}

	public void setAnimationSetupCallback(AnimationSetupCallback callback) {
		mCallback = callback;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@SuppressWarnings("deprecation")
					@Override
					public void onGlobalLayout() {
						createShader();
						setMaskScale(1.0f);

						if (mCallback != null) {
							mCallback.onSetupAnimation(SpotlightView.this);
						}

						getViewTreeObserver()
								.removeOnGlobalLayoutListener(this);
					}
				});
	}

	private void createShader() {
		View target = getRootView().findViewById(mTargetId);
		mTargetBitmap = createBitmap(target);
		Shader targetShader = createShader(mTargetBitmap);
		mPaint.setShader(targetShader);
	}

	private static Shader createShader(Bitmap b) {
		return new BitmapShader(b, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
	}

	private static Bitmap createBitmap(View target) {
		Bitmap b = Bitmap.createBitmap(target.getWidth(), target.getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		target.draw(c);
		return b;
	}

	private static Bitmap convertToAlphaMask(Bitmap b) {
		Bitmap a = Bitmap.createBitmap(b.getWidth(), b.getHeight(),
				Bitmap.Config.ALPHA_8);
		Canvas c = new Canvas(a);
		c.drawBitmap(b, 0.0f, 0.0f, null);
		return a;
	}

	public float computeMaskScale(float d) {
		// Let's assume the mask is square
		return d / (float) mMask.getHeight();
	}
}
