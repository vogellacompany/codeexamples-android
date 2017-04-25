package com.vogella.android.databinding;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class TemperatureData extends BaseObservable {
    private String location;
    private String celsius;
    public String url = "http://lorempixel.com/400/200/";

    public TemperatureData(String location, String celsius) {
        this.location = location;
        this.celsius = celsius;
    }

    @Bindable
    public String getCelsius() {
        return celsius;
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public  void setLocation(String location){
        this.location = location;
        notifyPropertyChanged(BR.location);
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
        notifyPropertyChanged(BR.celsius);
    }

}
