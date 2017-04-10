package com.vogella.android.temperatureconverter;

import java.math.BigDecimal;

public class ConverterUtil {
	// converts to celsius
	public static float convertFahrenheitToCelsius(float fahrenheit) {
		return round(((fahrenheit - 32) * 5 / 9),2);
	}

	// converts to fahrenheit
	public static float convertCelsiusToFahrenheit(float celsius) {
		return round(((celsius * 9) / 5) + 32,2);
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
