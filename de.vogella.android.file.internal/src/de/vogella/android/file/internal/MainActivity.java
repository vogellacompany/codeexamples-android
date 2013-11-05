package de.vogella.android.file.internal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View view) {
		writeFileToInternalStorage();
	}

	private void writeFileToInternalStorage() {
		String eol = System.getProperty("line.separator");
		BufferedWriter writer = null;
		try {
			File cacheDir = getCacheDir();
			File file = new File(cacheDir, "myfile3");
			FileWriter fileWriter = new FileWriter(file);
			writer = new BufferedWriter(fileWriter);
			writer.write("This is a test1." + eol);
			writer.write("This is a test2." + eol);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}