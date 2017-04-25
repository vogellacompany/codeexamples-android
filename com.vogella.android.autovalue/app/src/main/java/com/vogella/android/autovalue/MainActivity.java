package com.vogella.android.autovalue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        Task task = Task.builder().setId(1).setSummary("hello").setDescription("Testing").build();
        intent.putExtra("task", task);
        startActivity(intent);
    }
}
