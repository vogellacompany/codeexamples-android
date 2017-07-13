package com.vogella.android.viewmodel;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

public class MainActivity extends LifecycleActivity {

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final  MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        // above line does not comb
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
