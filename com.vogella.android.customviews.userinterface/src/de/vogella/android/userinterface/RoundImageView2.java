package de.vogella.android.userinterface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class RoundImageView2 extends RoundImageView {
	private Bitmap image;
	private Drawable placeholder;
	private Bitmap framedPhoto;

	public RoundImageView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		String attributeValue = attrs
				.getAttributeValue("android", "background");
		placeholder = getResources().getDrawable(R.drawable.pic101);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);
		int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);
		// Ensure this view is always square.
		int min = Math.min(measuredHeight, measuredWidth);
		setMeasuredDimension(min, min);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (placeholder == null && image == null) {
			return;
		}

		if (framedPhoto == null) {
			createFramedPhotoBorder(Math.min(getWidth(), getHeight()));
		}
		canvas.drawBitmap(framedPhoto, 0, 0, null);
	}

	private void createFramedPhotoBorder(int size) {
		Drawable imageDrawable = (image != null) ? new BitmapDrawable(
				getResources(), image) : placeholder;

		Bitmap output = Bitmap
				.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		RectF outerRect = new RectF(0, 0, size, size);
		float cornerRadius = size / 20f;

		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.RED);
		canvas.drawRoundRect(outerRect, cornerRadius, cornerRadius, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		imageDrawable.setBounds(0, 0, size, size);

		// Save the layer to apply the paint
		canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
		imageDrawable.draw(canvas);
		canvas.restore();

		// FRAMING THE PHOTO
		float border = size / 15f;

		// 1. Create offscreen bitmap link:
		Bitmap framedOutput = Bitmap.createBitmap(size, size,
				Bitmap.Config.ARGB_8888);
		Canvas framedCanvas = new Canvas(framedOutput);
		// End of Step 1

		// 2. Draw an opaque rounded rectangle link:
		RectF innerRect = new RectF(border, border, size - border, size
				- border - border);

		Paint innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		innerPaint.setColor(Color.RED);
		framedCanvas.drawRoundRect(innerRect, cornerRadius, cornerRadius,
				innerPaint);

		// 3. Set the Power Duff mode
		Paint outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		outerPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

		// 4. Draw a translucent rounded rectangle link:
		outerPaint.setColor(Color.argb(100, 0, 0, 0));
		framedCanvas.drawRoundRect(outerRect, cornerRadius, cornerRadius,
				outerPaint);

		// Draw the frame on top of original bitmap
		canvas.drawBitmap(framedOutput, 0f, 0f, null);
		Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint2.setColor(Color.WHITE);
		paint2.setTextSize(size / 20);
		canvas.drawText("This is my profile",
				size / 2 - paint2.measureText("This is my profile"), size
						- border / 2, paint2);
		framedPhoto = output;
	}

	public void setImageBitmap(Bitmap image) {
		this.image = image;
		this.framedPhoto = null;
		invalidate();
	}

}
