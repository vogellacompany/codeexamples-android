package com.vogella.android.customview.compoundview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vogella.android.view.compoundview.R;

public class ColorOptionsView extends LinearLayout {

	private View mValue;
	private ImageView mImage;

	public ColorOptionsView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ColorOptionsView, 0, 0);
		String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
		int valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor,
				android.R.color.holo_blue_light);
		a.recycle();

		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_color_options, this, true);

		TextView title = (TextView) getChildAt(0);
		title.setText(titleText);

		mValue = getChildAt(1);
		mValue.setBackgroundColor(valueColor);

	}

	public ColorOptionsView(Context context) {
		this(context, null);
	}

	public void setValueColor(int color) {
		mValue.setBackgroundColor(color);
	}

	public void setImageVisible(boolean visible) {
		mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

}
