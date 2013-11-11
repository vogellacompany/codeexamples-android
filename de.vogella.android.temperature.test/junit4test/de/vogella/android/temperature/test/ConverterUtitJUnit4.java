package de.vogella.android.temperature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.vogella.android.temperature.ConverterUtil;

public class ConverterUtitJUnit4 {

	public ConverterUtitJUnit4() {
		super();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertFahrenheitToCelsius() {
		float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
		// expected value is 212
		float expected = 212;
		// use this method because float is not precise
		assertEquals("Conversion from celsius to fahrenheit failed", expected,
				actual, 0.001);
	}

	@Test
	public void testConvertCelsiusToFahrenheit() {
		float actual = ConverterUtil.convertFahrenheitToCelsius(212);
		// expected value is 100
		float expected = 100;
		// use this method because float is not precise
		assertEquals("Conversion from celsius to fahrenheit failed", expected,
				actual, 0.001);
	}

}
