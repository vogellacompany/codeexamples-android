package com.vogella.android.autovalueparceable;

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
//        intent.putExtra("extra", new Student(1,"Mike","6"));
        Task task = Task.builder().setId(1).setSummary("hello").setDescription("Testing").build();
        intent.putExtra("extra", task);
        startActivity(intent);
    }
}
