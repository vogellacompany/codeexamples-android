package com.vogella.android.test.juntexamples;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by vogella on 19.06.16.
 */


@RunWith(Parameterized.class)
public class ConverterUtilTests {

    // fields used together with @Parameter must be public
    @Parameterized.Parameter(0)
    public int m1;
    @Parameterized.Parameter(1)
    public int m2;
    @Parameterized.Parameter(2)
    public int result;


    // creates the test data
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 1 , 2 ,2 }, { 5, 3, 15 },
                { 121, 4, 484 } };
        return Arrays.asList(data);
    }


//    @Test
//    public void testMultiplyException() {
//        MyClass tester = new MyClass();
//        assertEquals("Result", result, tester.multiply(m1, m2));
//    }
}
