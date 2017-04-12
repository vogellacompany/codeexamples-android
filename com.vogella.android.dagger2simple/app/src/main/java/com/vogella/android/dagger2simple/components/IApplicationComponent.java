package com.vogella.android.dagger2simple.components;

import com.vogella.android.dagger2simple.MyApplication;
import com.vogella.android.dagger2simple.modules.ActivityModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {ActivityModule.class, AndroidInjectionModule.class})
public interface IApplicationComponent {
    void inject(MyApplication application);
}