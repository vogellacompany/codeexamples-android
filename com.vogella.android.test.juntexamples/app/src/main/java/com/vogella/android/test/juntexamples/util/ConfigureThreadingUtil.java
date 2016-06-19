package com.vogella.android.test.juntexamples.util;

import android.content.Context;

import com.vogella.android.test.juntexamples.MyApplication;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by vogella on 19.06.16.
 */

public class ConfigureThreadingUtil {
    public static void configureThreadPool(MyApplication app){
        int numberOfThreads = app.getNumberOfThreads();
        // TODO use information to configure the thread pool


    }
}
