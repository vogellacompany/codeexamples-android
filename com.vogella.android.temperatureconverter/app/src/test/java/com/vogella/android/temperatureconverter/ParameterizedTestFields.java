package com.vogella.android.temperatureconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ParameterizedTestFields {

    // fields used together with @Parameter must be public
    @Parameter
    public float m1;
    @Parameter (value = 1)
    public float m2;


    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 46.4f , 8f }, { 5f, -15f }, { 24f, -4.44444465637207f } };
        return Arrays.asList(data);
    }


    @Test
    public void testMultiplyException() {
        float actual = ConverterUtil.convertFahrenheitToCelsius(m1);
        assertEquals("Result", m2, actual, 0.001);
    }



}
