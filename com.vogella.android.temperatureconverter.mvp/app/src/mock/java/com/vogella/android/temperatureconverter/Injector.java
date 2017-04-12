package com.vogella.android.temperatureconverter;

import static org.mockito.Mockito.mock;

/**
 * Created by vogella on 11.04.17.
 */

public class Injector {
    public static ConverterPresenter getConverterPresenter(ConverterActivity activity) {
        ConverterPresenter mock = mock(ConverterPresenter.class);
        // configure it here...
        return mock;
    }
}
