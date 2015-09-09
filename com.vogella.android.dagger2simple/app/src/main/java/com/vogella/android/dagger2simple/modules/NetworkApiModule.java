package com.vogella.android.dagger2simple.modules;

import com.vogella.android.dagger2simple.NetworkApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkApiModule {
    @Provides
    @Singleton
    public NetworkApi getNetwork(){
        return new NetworkApi();
    }
}
