package de.vogella.android.touchdelegate;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button mButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mButton = (Button) findViewById(R.id.delegated_button);
		View parent = findViewById(R.id.root);
		parent.post(new Runnable() {
			public void run() {
				// Post in the parent's message queue to make sure the parent
				// lays out its children before we call getHitRect()
				Rect delegateArea = new Rect();
				Button delegate = mButton;
				delegate.getHitRect(delegateArea);
				delegateArea.top -= 600;
				delegateArea.bottom += 600;
				delegateArea.left -= 600;
				delegateArea.right += 600;
				TouchDelegate expandedArea = new TouchDelegate(delegateArea,
						delegate);
				// give the delegate to an ancestor of the view we're
				// delegating the
				// area to
				if (View.class.isInstance(delegate.getParent())) {
					((View) delegate.getParent())
							.setTouchDelegate(expandedArea);
				}
			};
		});
	}
}