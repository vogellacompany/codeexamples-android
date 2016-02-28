package com.vogella.android.testapp;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.vogella.android.testapp.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String textFieldValue ="";
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityHandlers handler = new MainActivityHandlers();
        binding.setHandler(handler);
    }
}
