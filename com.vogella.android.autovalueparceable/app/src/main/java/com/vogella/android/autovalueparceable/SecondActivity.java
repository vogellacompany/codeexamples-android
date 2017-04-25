package com.vogella.android.autovalueparceable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textview = (TextView) findViewById(R.id.result);

        Object extra = getIntent().getExtras().get("extra");
        textview.setText(extra.toString());

    }
}
