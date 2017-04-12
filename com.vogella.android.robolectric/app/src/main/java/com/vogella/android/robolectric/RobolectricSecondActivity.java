package com.vogella.android.robolectric;

import android.app.Activity;
import android.os.Bundle;

public class RobolectricSecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robolectric);
    }
}
