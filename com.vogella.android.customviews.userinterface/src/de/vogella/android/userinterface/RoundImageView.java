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
import android.view.View;

public class RoundImageView extends View {
	private Bitmap image;
	private Drawable placeholder;
	private Bitmap framedPhoto;

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		String attributeValue = attrs
				.getAttributeValue("android", "background");
		placeholder = getResources().getDrawable(R.drawable.pray);
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

		// Sanity Check
		if (placeholder == null && image == null) {
			return;
		}

		// Only calculate
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
		framedPhoto = output;
	}

	public void setImageBitmap(Bitmap image) {
		this.image = image;
		this.framedPhoto = null;
		invalidate();
	}

}
