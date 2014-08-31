package com.vogella.android.test.fileaccess.test;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;
import android.test.mock.MockContext;
import com.vogella.android.test.fileaccess.Util;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FileTest extends TestCase {
	
	public int fileWrites = 0;

	public class StubOutputStream extends FileOutputStream {

		public StubOutputStream() throws FileNotFoundException {
			super(FileDescriptor.out);
		}

		// count number of calls
		@Override
		public void write(byte[] buffer) throws IOException {
			fileWrites++;
		}
	}
	MockContext context = new MockContext() {
		public java.io.FileOutputStream openFileOutput(String name, int mode)
				throws java.io.FileNotFoundException {
			return new StubOutputStream();
		};
	};
	
	public void testWriteConfigurationShouldAccessFileSystemTwice() {
		assertEquals("No file writes yet", fileWrites, 0);
		Util.writeConfiguration(context);
		assertEquals("Failed to write twice", 2, fileWrites);
	}
}
