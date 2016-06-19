package com.vogella.android.test.juntexamples;

import android.app.Application;

/**
 * Created by vogella on 19.06.16.
 */

public class MyApplication extends Application{
    int thread = 4;
    public int getNumberOfThreads() {
        return thread;
    }
    public void setNumberOfThreads(int thread) {
        this.thread= thread;

    }
}
