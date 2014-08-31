package com.vogella.android.test.renamingdelegationcontext.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.vogella.android.test.renamingdelegationcontext.MainActivity;

public class MockContextExamplesTests extends
		ActivityUnitTestCase<MainActivity> {
	boolean readDone = false;

	public MockContextExamplesTests() {
		super(MainActivity.class);
	}

	public class MyMockContext extends RenamingDelegatingContext {

		private static final String PREFIX = "test.";
	
		public MyMockContext(Context context) {
			super(context, PREFIX);
			makeExistingFilesAndDbsAccessible();
		}

		@Override
		public FileInputStream openFileInput(String name)
				throws FileNotFoundException {
			Log.d("TEST", "file location of " + name + " is "
					+ getFileStreamPath(name));
			readDone=true;
			return super.openFileInput(name);
		}

	}

	public void testSampleTextDisplayed() {
		Context mockContext = new MyMockContext(getInstrumentation()
				.getTargetContext());
		setActivityContext(mockContext);
		startActivity(new Intent(), null, null);
		
		assertTrue(readDone);
		final MainActivity activity = getActivity();
		assertNotNull(activity);
		assertEquals("lalalla", activity.getText());

	}

	public void testRealTextDisplayed() {
		// real context
		setActivityContext(getInstrumentation().getTargetContext());
		startActivity(new Intent(), null, null);
		final MainActivity activity = getActivity();
		assertNotNull(activity);
		assertFalse("READDATA".equals(activity.getText()));

	}

}
