package com.vogella.android.listview3d;

import de.vogella.android.listview3d.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {
	final static int ELEMENT_COUNT = 400;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] elements = new String[ELEMENT_COUNT];
        
        for (int i = 0; i< ELEMENT_COUNT; i++) {
        	elements[i] = String.valueOf(i);
        }
        
        MyAdapter adapter = new MyAdapter(this,elements);

        final ListView list = (ListView) findViewById(R.id.list3d);
        list.setDivider( null ); 
        list.setAdapter(adapter);
    }
}