package com.vogella.android.github.issuetracker;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationModule {
    @ContributesAndroidInjector(modules = GithubApiModule.class)
    abstract MainActivity contributeMainActivity();
}
