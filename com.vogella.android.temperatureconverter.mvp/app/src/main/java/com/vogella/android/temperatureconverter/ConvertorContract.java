package com.vogella.android.temperatureconverter;


public interface ConvertorContract {
    interface View {
        void updateResultValue(float result);
        void enableCelsiusSelection(boolean celsiusSelection);
        void showError();
    }

    interface Presenter {
        void convertFahrenheitToCelsius(float fahrenheit);
        void convertCelsiusToFahrenheit(float celsius);
        boolean validateInput(String inputToValidate);
    }
}
