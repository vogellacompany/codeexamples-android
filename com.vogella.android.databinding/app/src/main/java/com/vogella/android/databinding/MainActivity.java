package com.vogella.android.databinding;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import com.vogella.android.databinding.databinding.ActivityMainBinding;

public class MainActivity extends Activity implements MainActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);
        TemperatureData temperatureData = new TemperatureData("Hamburg", "10");
        binding.setTemp(temperatureData);
        binding.setPresenter(mainActivityPresenter);
    }

    @Override
    public void showData(TemperatureData temperatureData) {
        String celsius = temperatureData.getCelsius();
        Toast.makeText(this, celsius, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
