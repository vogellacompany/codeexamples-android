package com.vogella.android.daggerjunitmockito;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = TestModule.class)
public interface TestComponent extends MyComponent {
    void inject(MainServiceDaggerTest test);
}