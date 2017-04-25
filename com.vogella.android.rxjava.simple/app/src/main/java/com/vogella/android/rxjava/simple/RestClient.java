package com.vogella.android.rxjava.simple;

import android.content.Context;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a mock REST Client. It simulates making blocking calls to an REST endpoint.
 */
public class RestClient {
    private Context mContext;

    public RestClient(Context context) {
        mContext = context;
    }

    public List<String> getFavoriteTvShows() {
        SystemClock.sleep(5000);// "Simulate" the delay of network.
        return createBooks();
    }

    public List<String> getFavoriteTvShowsWithException() {
        SystemClock.sleep(5000);// "Simulate" the delay of network.
        throw new RuntimeException("Failed to load");
    }

    private List<String> createBooks() {
        List<String> books = new ArrayList<>();
        books.add("Lord of the Rings");
        books.add("The dark elf");
        books.add("Eclipse Introduction");
        books.add("Histor book");
        books.add("Der kleine Prinz");
        books.add("7 habits of highly effective people");
        books.add("Other book 1");
        books.add("Other book 2");
        books.add("Other book 3");
        books.add("Other book 4");
        books.add("Other book 5");
        books.add("Other book 6");
        return books;
    }

    public List<String> searchForCity(String searchString) {
        try {
            // "Simulate" the delay of network.
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getMatchingCities(searchString);
    }

    private List<String> getMatchingCities(String searchString) {
        if (searchString.isEmpty()) {
            return new ArrayList<>();
        }

        String[] cities = mContext.getResources().getStringArray(R.array.city_list);
        List<String> toReturn = new ArrayList<>();
        for (String city : cities) {
            if (city.toLowerCase().startsWith(searchString.toLowerCase())) {
                toReturn.add(city);
            }
        }
        return toReturn;
    }
}