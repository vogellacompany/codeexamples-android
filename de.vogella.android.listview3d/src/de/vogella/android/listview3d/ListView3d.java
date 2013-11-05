package de.vogella.android.listview3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class ListView3d extends ListView {

	/** Ambient light intensity */
	private static final int AMBIENT_LIGHT = 55;
	/** Diffuse light intensity */
	private static final int DIFFUSE_LIGHT = 200;
	/** Specular light intensity */
	private static final float SPECULAR_LIGHT = 70;
	/** Shininess constant */
	private static final float SHININESS = 200;
	/** The max intensity of the light */
	private static final int MAX_INTENSITY = 0xFF;

	private final Camera mCamera = new Camera();
	private final Matrix mMatrix = new Matrix();
	/** Paint object to draw with */
	private final Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

	public ListView3d(Context context, AttributeSet attrs) {
		super(context, attrs);
		// anti aliasing wont work with hardware acceleration
		// thats why the icons have a one pixel wide transparent border
		mPaint.setAntiAlias(true);
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		Bitmap bitmap = getChildDrawingCache(child);
		// (top,left) is the pixel position of the child inside the list
		final int top = child.getTop();
		final int left = child.getLeft();
		// center point of child
		final int childCenterY = child.getHeight() / 2;
		final int childCenterX = child.getWidth() / 2;
		// center of list
		final int parentCenterY = getHeight() / 2;
		// center point of child relative to list
		final int absChildCenterY = child.getTop() + childCenterY;
		// distance of child center to the list center
		final int distanceY = parentCenterY - absChildCenterY;
		// radius of imaginary cirlce
		final int r = getHeight() / 2;

		prepareMatrix(mMatrix, distanceY, r);

		mMatrix.preTranslate(-childCenterX, -childCenterY);
		mMatrix.postTranslate(childCenterX, childCenterY);
		mMatrix.postTranslate(left, top);

		canvas.drawBitmap(bitmap, mMatrix, mPaint);
		return false;

	}

	private void prepareMatrix(final Matrix outMatrix, int distanceY, int r) {
		// clip the distance
		final int d = Math.min(r, Math.abs(distanceY));
		// use circle formula
		final float translateZ = (float) Math.sqrt((r * r) - (d * d));

		// solve for t: d = r*cos(t)
		double radians = Math.acos((float) d / r);
		double degree = 90 - (180 / Math.PI) * radians;

		mCamera.save();
		mCamera.translate(0, 0, r - translateZ);
		mCamera.rotateX((float) degree);
		if (distanceY < 0) {
			degree = 360 - degree;
		}
		mCamera.rotateY((float) degree);
		mCamera.getMatrix(outMatrix);
		mCamera.restore();

		// highlight elements in the middle
		mPaint.setColorFilter(calculateLight((float) degree));
	}

	private Bitmap getChildDrawingCache(final View child) {
		Bitmap bitmap = child.getDrawingCache();
		if (bitmap == null) {
			child.setDrawingCacheEnabled(true);
			child.buildDrawingCache();
			bitmap = child.getDrawingCache();
		}
		return bitmap;
	}

	private LightingColorFilter calculateLight(final float rotation) {
		final double cosRotation = Math.cos(Math.PI * rotation / 180);
		int intensity = AMBIENT_LIGHT + (int) (DIFFUSE_LIGHT * cosRotation);
		int highlightIntensity = (int) (SPECULAR_LIGHT * Math.pow(cosRotation,
				SHININESS));
		if (intensity > MAX_INTENSITY) {
			intensity = MAX_INTENSITY;
		}
		if (highlightIntensity > MAX_INTENSITY) {
			highlightIntensity = MAX_INTENSITY;
		}
		final int light = Color.rgb(intensity, intensity, intensity);
		final int highlight = Color.rgb(highlightIntensity, highlightIntensity,
				highlightIntensity);
		return new LightingColorFilter(light, highlight);
	}
}
