package com.vogella.android.daggerjunitmockito;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends Activity {

    @Inject
    MainService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toast.makeText(this, , Toast.LENGTH_LONG).show();
    }
}
