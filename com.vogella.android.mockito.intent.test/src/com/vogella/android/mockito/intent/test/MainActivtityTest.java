package com.vogella.android.mockito.intent.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

import com.vogella.android.mockito.intent.MainActivity;
import com.vogella.android.mockito.intent.MyApp;

public class MainActivtityTest extends ActivityUnitTestCase<MainActivity> {
	public MainActivtityTest() {
		super(MainActivity.class);
	}

	@Mock
	Context context;
	private MyApp myApp;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockitoAnnotations.initMocks(this);

		myApp = mock(MyApp.class);
		when(myApp.getNumber()).thenReturn(42);
		setApplication(myApp);

		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		startActivity(intent, null, null);
	}

	public void testQuery() throws Exception {
		Intent intent = MainActivity.createQuery(context, "query", "value");
		assertNotNull(intent);
		Bundle extras = intent.getExtras();
		assertNotNull(extras);
		assertEquals("query", extras.getString("QUERY"));
		assertEquals("value", extras.getString("VALUE"));
	}

	public void testView() throws Exception {
		MainActivity activity = getActivity();
		TextView target = (TextView) activity
				.findViewById(com.vogella.android.mockito.intent.R.id.target);
		assertEquals(target.getText(), String.valueOf(42));
		verify(myApp).getNumber();
	}
}
