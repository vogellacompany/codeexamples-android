package com.vogella.android.temperatureconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ConverterActivity extends Activity implements ConvertorContract.View {
    private EditText text;
    ConverterPresenter presenter;
    RadioButton celsiusButton;
    RadioButton fahrenheitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.inputValue);
        celsiusButton = (RadioButton) findViewById(R.id.radio0);
        fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
        presenter = Injector.getConverterPresenter(this);
    }

    // this method is called at button click because we assigned the name to the
    // "OnClick" property of the button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                String s = text.getText().toString();
                if (presenter.validateInput(s)) {

                    float inputValue = Float.parseFloat(s);
                    if (celsiusButton.isChecked()) {
                        presenter.convertFahrenheitToCelsius(inputValue);
                    } else {
                        presenter.convertCelsiusToFahrenheit(inputValue);

                    }
                    break;
                }
        }
    }

    @Override
    public void updateResultValue(float result) {
        text.setText(String.valueOf(result));
    }

    @Override
    public void enableCelsiusSelection(boolean celsiusSelection) {
        fahrenheitButton.setChecked(!celsiusSelection);
        celsiusButton.setChecked(celsiusSelection);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show();
    }
}