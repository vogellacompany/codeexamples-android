package com.vogella.android.daggerjunitmockito;

import javax.inject.Inject;

/**
 * Created by vogella on 14.06.16.
 */

public class MainService {
    private RestService restService;
    private MyPrinter printer;

    @Inject
    public MainService(RestService restService,
                       MyPrinter printer) {
        this.restService = restService;
        this.printer = printer;
    }

    public void doSomething() {
        String s = restService.getSomething();
        printer.print(s.toUpperCase());
    }
}
