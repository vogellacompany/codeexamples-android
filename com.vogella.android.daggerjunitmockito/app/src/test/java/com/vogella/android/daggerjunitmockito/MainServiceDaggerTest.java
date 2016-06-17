package com.vogella.android.daggerjunitmockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;

import javax.inject.Inject;

import dagger.Component;

import static org.mockito.Mockito.*;

public class MainServiceDaggerTest {

    @Inject
    RestService restService;

    @Inject MyPrinter myPrinter;

    @Inject MainService mainService;

    @Before
    public void setUp() {
        TestComponent build = DaggerTestComponent.builder().testModule(new TestModule()).build();
        build.inject(this);
    }

    @Test
    public void testDoSomething() {
        when(restService.getSomething()).thenReturn("abc");

        mainService.doSomething();

        verify(myPrinter).print("ABC");
    }

//    private Component verify(MyPrinter myPrinter) {
//    }
}