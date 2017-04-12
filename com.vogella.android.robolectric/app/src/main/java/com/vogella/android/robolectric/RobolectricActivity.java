package com.vogella.android.robolectric;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RobolectricActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robolectric);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startNextActivity:
                Intent intent = new Intent(this, RobolectricSecondActivity.class);
                startActivity(intent);
                break;
            case R.id.showToast:
                Toast.makeText(this, "Lala", Toast.LENGTH_SHORT).show();
        }

    }
}
