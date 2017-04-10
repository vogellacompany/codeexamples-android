package com.vogella.android.temperatureconverter;

import android.support.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ConverterUtilTest2 {


    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
        // expected value is 212
        float expected = 212;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }

    @Test
    public void testConvertCelsiusToFahrenheit() {
        float actual = ConverterUtil.convertFahrenheitToCelsius(212);
        // expected value is 100
        float expected = 100;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }
}
