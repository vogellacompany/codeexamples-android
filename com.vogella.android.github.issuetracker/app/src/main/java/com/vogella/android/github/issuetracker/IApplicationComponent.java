package com.vogella.android.github.issuetracker;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class})
public interface IApplicationComponent extends AndroidInjector<MyApplication> {
}
