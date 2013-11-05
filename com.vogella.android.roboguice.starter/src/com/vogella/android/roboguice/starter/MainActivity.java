package com.vogella.android.roboguice.starter;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;

public class MainActivity extends RoboActivity {

	@InjectView(R.id.text)
	TextView name;

	@InjectView(R.id.imageView1)
	ImageView thumbnail;

	@InjectResource(R.drawable.ic_launcher)
	Drawable icon;

	@InjectResource(R.string.app_name)
	String myName;

	// System service
	@Inject
	LocationManager loc;

	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

}
