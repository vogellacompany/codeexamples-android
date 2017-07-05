package com.vogella.android.github.issuetracker;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyApplicationModule {
    @ContributesAndroidInjector(modules = GithubApiModule.class)
    abstract MainActivity contributeMainActivity();
}
