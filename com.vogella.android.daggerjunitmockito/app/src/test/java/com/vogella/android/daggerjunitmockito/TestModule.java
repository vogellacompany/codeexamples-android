package com.vogella.android.daggerjunitmockito;
import android.support.annotation.NonNull;

import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule  {
    @Provides
    @Singleton
    @NonNull
    public MyPrinter provideMyPrinter() {
        return Mockito.mock(MyPrinter.class);
    }

    @Provides
    @Singleton
    @NonNull
    public RestService provideRestService() {
        return Mockito.mock(RestService.class);
    }
}