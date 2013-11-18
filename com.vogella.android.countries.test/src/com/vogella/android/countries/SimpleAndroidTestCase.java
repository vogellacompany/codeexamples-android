package com.vogella.android.countries;

import java.util.List;

import android.content.Context;
import android.test.AndroidTestCase;
import android.view.View;
import android.widget.FrameLayout;

import com.vogella.android.countries.CountryUtils;
import com.vogella.android.countries.MainActivity;

/** Simple test case class for our application */
public class SimpleAndroidTestCase extends AndroidTestCase {

	/** Test our StringListAdapter class */
	public void testStringListAdapter() {

		Context context = getContext();
		List<String> countries = CountryUtils.getCountries();
		MainActivity.StringListAdapter stringListAdapter = new MainActivity.StringListAdapter(
				context, countries);
		int count = stringListAdapter.getCount();
		assertTrue(count > 0);

		FrameLayout layout = new FrameLayout(context);

		// Here we ask adapter to create a new View for us
		// TODO Create a new View by calling getView(0, null, layout) method of
		// the adapter.
		View view = stringListAdapter.getView(0, null, layout);
		assertTrue(view instanceof android.widget.TextView);

		// Here we create another view by reusing previous view
		View view2 = stringListAdapter.getView(1, view, layout);
		 assertSame(view, view2);
		// TODO Assert view2 is the same instance as view. Use assertSame() for
		// that.

	}
}