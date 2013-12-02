package com.vogella.android.countries;

import android.content.Context;
import android.test.AndroidTestCase;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.vogella.android.countries.MainActivity.StringListAdapter;

/** Simple test case class for our application */
public class SimpleAndroidTestCase extends AndroidTestCase {

	/** Test our StringListAdapter class */
	public void testStringListAdapter() {
		
		Context context = getContext();
		
		// TODO Create new instance of com.vogella.android.countries.MainActivity.StringListAdapter. 
		//      Initialize it with context and the list returned from CountryUtils.getCountries() 
		//      method.
		// TODO Assert getCount() method returns a positive value
		
		FrameLayout layout = new FrameLayout(context);

		// Here we ask adapter to create a new View for us 
		// TODO Create a new View by calling getView(0, null, layout) method of the adapter.
		// TODO Assert returned view is instanceof android.widget.TextView. Use assertTrue() method.
		
		// Here we create another view by reusing previous view
		View view2 = adapter.getView(1, view, layout);
		// TODO Assert view2 is the same instance as view. Use assertSame() for that.
		
	}
}