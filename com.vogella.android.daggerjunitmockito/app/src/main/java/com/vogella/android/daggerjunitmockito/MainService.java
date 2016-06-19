package com.vogella.android.daggerjunitmockito;

import javax.inject.Inject;

/**
 * Created by vogella on 14.06.16.
 */

public class MainService {
    private RestService restService;
    private MyPrinter printer;

    @Inject
    public MainService(RestService restService,  MyPrinter printer) {
        this.restService = restService;
        this.printer = printer;
    }

    public void doSomething() {
        if (restService == null) {
           System.out.println("restService not allowed to be null");
        }
        String s = restService.getSomething();
        System.out.println(s);
        if (printer == null) {
            System.out.println("printer not allowed to be null");
        }

        printer.print(s.toUpperCase());
    }
}
