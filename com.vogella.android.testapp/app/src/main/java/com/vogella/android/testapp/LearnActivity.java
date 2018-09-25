package com.vogella.android.testapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        List<Integer> buttons = Arrays.asList(R.id.one, R.id.two, R.id.three,
                R.id.four, R.id.five, R.id.six, R.id.seven,
                R.id.eight, R.id.nine, R.id.zero, R.id.delete);
        for(Integer i: buttons) {
            View b = findViewById(i);
            b.setOnClickListener(this); // calling onClick() method

        }
    }

    public void onClick(View view) {
    switch (view.getId()) {
      // TODO your logic to evaluate the indivual button
    }
}
}
