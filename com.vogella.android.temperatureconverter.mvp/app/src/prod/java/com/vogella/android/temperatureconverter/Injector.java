package com.vogella.android.temperatureconverter;

/**
 * Created by vogella on 11.04.17.
 */

public class Injector {
    public static ConverterPresenter getConverterPresenter(ConverterActivity activity) {
        return new ConverterPresenter(activity);
    }
}
