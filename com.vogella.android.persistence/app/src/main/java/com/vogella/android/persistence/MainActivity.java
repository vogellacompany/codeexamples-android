package com.vogella.android.persistence;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase database = AppDatabase.getInMemoryDatabase(getApplicationContext());
        for (int i = 0; i <10; i++) {
            Task build = Task.builder().setId(i).setSummary("Testing " + i).setDescription("More ..." + i).build();
            database.taskModel().addTask(build);
        }
        List<Task> allTasks = database.taskModel().getAllTasks();
        TextView textView = findViewById(R.id.result);
        textView.setText(allTasks.toString());

    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
