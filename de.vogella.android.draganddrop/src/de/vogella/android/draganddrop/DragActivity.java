package de.vogella.android.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragActivity extends Activity implements OnDragListener {
	private Drawable enterShape;
	private Drawable normalShape;
	OnTouchListener dragListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			// start move on a touch event
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			}
			return false;

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		normalShape = getResources().getDrawable(R.drawable.shape);

		findViewById(R.id.myimage1).setOnTouchListener(dragListener);
		findViewById(R.id.myimage2).setOnTouchListener(dragListener);
		findViewById(R.id.myimage3).setOnTouchListener(dragListener);
		findViewById(R.id.myimage4).setOnTouchListener(dragListener);

		findViewById(R.id.topleft).setOnDragListener(this);
		findViewById(R.id.topright).setOnDragListener(this);
		findViewById(R.id.bottomleft).setOnDragListener(this);
		findViewById(R.id.bottomright).setOnDragListener(this);

	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED:
			// Do nothing
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			v.setBackground(enterShape);
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			v.setBackground(normalShape);
			break;
		case DragEvent.ACTION_DROP:
			// view dropped, reassign the view to the new ViewGroup
			View view = (View) event.getLocalState();
			ViewGroup owner = (ViewGroup) view.getParent();
			owner.removeView(view);
			LinearLayout container = (LinearLayout) v;
			container.addView(view);
			view.setVisibility(View.VISIBLE);
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			v.setBackground(normalShape);
		default:
			break;
		}
		return true;
	}
}