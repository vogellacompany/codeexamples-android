package com.vogella.android.test.juntexamples.util;

import android.content.Context;

import java.io.FileOutputStream;

public class WriteConfigurationUtil {
    public static void writeConfiguration(Context ctx ) {
		try (FileOutputStream openFileOutput =
			 ctx.openFileOutput( "config.txt", Context.MODE_PRIVATE);) {
		
			openFileOutput.write("This is a test1.".getBytes());
			openFileOutput.write("This is a test2.".getBytes());
		} catch (Exception e) {
			// not handled
		}
	}
}
