package com.vogella.android.daggerjunitmockito;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule  {
    @Provides
    public MyPrinter provideMyPrinter() {
        return Mockito.mock(MyPrinter.class);
    }

    @Provides
    public RestService provideRestService() {
        return Mockito.mock(RestService.class);
    }
}