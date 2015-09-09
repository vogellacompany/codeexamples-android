package com.vogella.android.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final ImportClass instance;

    public AppModule(ImportClass instance) {
        this.instance = instance;
    }

    @Provides
    @Singleton
    ImportClass providesImportClass() {
        return new ImportClass();
    }

    @Provides
    @Singleton
    TestClass1 providesTestClass1() {
        return new TestClass1();
    }

    @Provides
    @Singleton
    TestClass2 providesTestClass2() {
        return new TestClass2();
    }

}