package com.vogella.android.temperatureconverter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

public class ConverterUtilTest {


    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
        // expected value is 212
        float expected = 212;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }

    @Test
    public void testConvertFahrenheitToCelsiusHamcrest() {
        float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
        // expected value is 212
        float expected = 212;

        // Hamcrest
        assertThat("Conversion from celsius to fahrenheit failed", expected, is(actual));
    }

    // Hamcrest
    @Test
    public void checkListContent() {
        List<Integer> list = Arrays.asList(5, 2, 4);
        assertThat(list, hasSize(3));
        assertThat(list, containsInAnyOrder(5, 2, 4));
        assertThat(list, containsInAnyOrder(2, 4, 5));
        assertThat(list, everyItem(greaterThan(1)));
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
