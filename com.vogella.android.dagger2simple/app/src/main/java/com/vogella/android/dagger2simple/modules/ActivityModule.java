package com.vogella.android.dagger2simple.modules;

import android.app.Activity;

import com.vogella.android.dagger2simple.MainActivity;
import com.vogella.android.dagger2simple.components.IMainActivitySubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {IMainActivitySubcomponent.class})
public abstract class ActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindYourActivityInjectorFactory(IMainActivitySubcomponent.Builder builder);
}
