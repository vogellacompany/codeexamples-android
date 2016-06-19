package com.vogella.android.daggerjunitmockito;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

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
        TestComponent build = DaggerTestComponent.builder().build();
        build.inject(this);
    }

    @Test
    public void testDoSomething() {
        System.out.println("Rest service " + restService);
        System.out.println("Printer " + myPrinter);
        System.out.println("MainService " + mainService);

        when(restService.getSomething()).thenReturn("abc");
        System.out.println(restService.getSomething());

        mainService.doSomething();

        verify(myPrinter).print("ABC");
    }

//    private Component verify(MyPrinter myPrinter) {
//    }
}