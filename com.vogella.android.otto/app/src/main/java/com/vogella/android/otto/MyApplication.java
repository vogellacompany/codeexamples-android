package com.vogella.android.otto;

import android.app.Application;

import com.squareup.otto.Bus;

public class MyApplication extends Application{

    Bus ottoBus;

    @Override
    public void onCreate() {
        super.onCreate();
        ottoBus = new Bus();
    }

    public Bus getOttoBus() {
        return ottoBus;
    }
}
