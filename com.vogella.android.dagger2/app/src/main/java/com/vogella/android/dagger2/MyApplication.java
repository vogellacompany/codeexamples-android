package com.vogella.android.dagger2;

import android.app.Application;

/**
 * Created by vogella on 21.07.15.
 */
public class MyApplication  extends Application{
    public static ImportClassComponent build;
    @Override
    public void onCreate() {
        super.onCreate();
        build = DaggerImportClassComponent.builder().build();
    }
}
