package com.vogella.android.viewmodel;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final  MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);
        model.getUsers().observe(MainActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                // update UI
                TextView textView = findViewById(R.id.result);
                textView.setText(model.getUsers().getValue().get(0).toString());
            }
        });
    }
}
