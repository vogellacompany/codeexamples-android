package com.vogella.android.traceview;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<String> list = createValues();
		MyArrayAdapter adapter = new MyArrayAdapter(this, list);
		setListAdapter(adapter);
	}

	private static List<String> createValues() {
		SecureRandom random = new SecureRandom();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000; i++) {
			String string = new BigInteger(130, random).toString(32);
			
			list.add(string);
		}
		return list;
	}

}
