package com.vogella.android.countries;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.text.TextUtils;

/** Class for getting country related information from the system */
public class CountryUtils {

	/** Returns a list of countries known by Android */
	public static List<String> getCountries() {
		
		// get available Android locales
		Locale[] locales = Locale.getAvailableLocales();
		
		// create a list of countries
		ArrayList<String> countries = new ArrayList<String>();
		
		// iterate through all locales
		for (Locale locale : locales) {
			
			// get country display name
			String country = locale.getDisplayCountry();
			
			// add country if display name is not empty
			if (!TextUtils.isEmpty(country)) {
				countries.add(country);
			}
		}
		return countries;
	}
	
}
