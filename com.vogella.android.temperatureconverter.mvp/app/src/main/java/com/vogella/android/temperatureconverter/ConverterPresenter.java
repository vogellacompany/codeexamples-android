package com.vogella.android.temperatureconverter;


public class ConverterPresenter implements ConvertorContract.Presenter {

    private ConvertorContract.View view;

    public ConverterPresenter(ConvertorContract.View view) {
        this.view = view;
    }

    @Override
    public void convertFahrenheitToCelsius(float fahrenheit) {
        view.updateResultValue(ConverterUtil.convertFahrenheitToCelsius(fahrenheit));
        view.enableCelsiusSelection(false);

    }

    @Override
    public void convertCelsiusToFahrenheit(float celsius) {
        view.updateResultValue(ConverterUtil.convertCelsiusToFahrenheit(celsius));
        view.enableCelsiusSelection(true);
    }

    @Override
    public boolean validateInput(String inputToValidate) {
        if (inputToValidate== null && !inputToValidate.isEmpty()) {
            view.showError();
            return false;
        }
        return true;
    }
}
