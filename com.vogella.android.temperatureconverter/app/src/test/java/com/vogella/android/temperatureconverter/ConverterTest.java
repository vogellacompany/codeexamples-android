package com.vogella.android.temperatureconverter;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void testConvert() {
        float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
        float expected = 212;
        assertEquals("Conversion from celsius to fahrenheit failed", expected,
                actual, 0.001);
    }
}
