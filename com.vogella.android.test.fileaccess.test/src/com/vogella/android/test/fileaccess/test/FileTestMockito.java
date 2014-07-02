package com.vogella.android.test.fileaccess.test;

import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.vogella.android.test.fileaccess.Util;

public class FileTestMockito extends InstrumentationTestCase {
	public int fileWrites = 0;

	@Mock
	Context context;

	protected void setUp() throws Exception {
		super.setUp();
		System.setProperty("dexmaker.dexcache", getInstrumentation()
				.getTargetContext().getCacheDir().toString());
		// Initialize the mock
		MockitoAnnotations.initMocks(this);
	};

	public void testFileApplication() {

		FileOutputStream mock = Mockito.mock(FileOutputStream.class);
		try {
			
			when(context.openFileOutput(Mockito.anyString(), Mockito.anyInt())).thenReturn(
					mock);
			Mockito.verify(mock, Mockito.times(0)).write(Mockito.anyByte());
			Util.writeConfiguration(context);
			Mockito.verify(mock, Mockito.times(1)).write("This is a test1.".getBytes());
			Mockito.verify(mock, Mockito.times(1)).write("This is a test2.".getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
