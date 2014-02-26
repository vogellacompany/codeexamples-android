package com.example.android.rssfeed;

import android.app.Fragment;
import android.os.Bundle;

public class HeadlessFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

}