package com.vogella.android.textautocomplete;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView view = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        String[] androidversion = getResources().
                getStringArray(R.array.android_versoins);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (this,R.layout.row_layout,
                                R.id.textView,
                                androidversion);
        view.setThreshold(1);
        view.setAdapter(adapter);
    }
}
