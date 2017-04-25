package com.vogella.android.autovalue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Task task = (Task) getIntent().getExtras().get("task");

        Toast.makeText(this, task.toString(), Toast.LENGTH_SHORT).show();
    }

}