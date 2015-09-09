package com.vogella.android.dagger2;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
        }
)
public interface ImportClassComponent  {

    void inject (MainActivity activity);
}