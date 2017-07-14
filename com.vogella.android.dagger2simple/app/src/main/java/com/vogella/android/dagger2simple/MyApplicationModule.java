package com.vogella.android.dagger2simple;


import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyApplicationModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeAcitivityInjector();
}
