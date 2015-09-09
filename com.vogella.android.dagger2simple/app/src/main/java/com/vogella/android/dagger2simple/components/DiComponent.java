package com.vogella.android.dagger2simple.components;

import android.app.Activity;

import com.vogella.android.dagger2simple.MainActivity;
import com.vogella.android.dagger2simple.NetworkApi;
import com.vogella.android.dagger2simple.modules.NetworkApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkApiModule.class})

public interface DiComponent {
    // to update the fields in your activities
    void inject(MainActivity activity);
}
