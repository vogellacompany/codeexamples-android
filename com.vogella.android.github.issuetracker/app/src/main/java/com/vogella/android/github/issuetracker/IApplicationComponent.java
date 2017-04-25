package com.vogella.android.github.issuetracker;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {AndroidInjectionModule.class,ActivityModule.class })
public interface IApplicationComponent extends AndroidInjector<MyApplication> {
}
