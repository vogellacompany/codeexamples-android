package com.vogella.android.test.juntexamples;

import com.vogella.android.test.juntexamples.util.ConverterUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


/**
 * Created by vogella on 19.06.16.
 */


@RunWith(Parameterized.class)
public class ConverterUtilTests {

    // fields used together with @Parameter must be public
    @Parameterized.Parameter(0)
    public float celsius;
    @Parameterized.Parameter(1)
    public float result;


    // creates the test data
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 0 , 32 }, { 5, 41 },
                { 35, 95 } };
        return Arrays.asList(data);
    }

    @Test
    public void testMultiplyException() {
        ConverterUtil util = new ConverterUtil();
        float calculatedResult = util.convertCelsiusToFahrenheit(celsius);
        assertEquals("Result", result, calculatedResult, 0.1);
    }
}
