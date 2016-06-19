package com.vogella.android.test.juntexamples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
        String mykey = extras.getString("MYKEY");
        TextView view = (TextView) findViewById(R.id.target);
        if (mykey != null && mykey.length()>0) {
            view.setText(mykey);
        } else {
            view.setText("Unknown intent");
        }
        }



    }
}
