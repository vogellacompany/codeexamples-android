package com.vogella.android.test.juntexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vogella.android.test.juntexamples.model.TolkienCharacter;

import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataService dataService = new DataService();
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<TolkienCharacter> adapter = new ArrayAdapter<TolkienCharacter>(this, android.R.layout.simple_list_item_1, android.R.id.text1, fellowship);
        listView.setAdapter(adapter);

    }
}
