package com.vogella.android.rxjava.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by vogella on 21.04.17.
 */

public class SwitcherActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher);
    }

    public void onClick(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.first:
                i = new Intent(this, RxJavaSimpleActivity.class);
                break;
            case R.id.second:
                i = new Intent(this, MainActivity.class);
                break;
            case R.id.third:
                i = new Intent(this, BooksActivity.class);
                break;
        }
        startActivity(i);
    }
}
