package com.vogella.android.daggerjunitmockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {
    @Provides @Singleton
    public RestService provideRestService() {
        return new RestService();
    }

    @Provides
    @Singleton public MyPrinter provideMyPrinter() {
        return new MyPrinter();
    }
}