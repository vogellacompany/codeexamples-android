package com.vogella.android.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateUserActivity extends AppCompatActivity {

    private EditText userName;
    boolean male = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        userName = (EditText) findViewById(R.id.username);
        RadioButton femaleButton = findViewById(R.id.female);
        femaleButton.setAlpha(0.5f);
        RadioButton maleButton = findViewById(R.id.male);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        maleButton.setAlpha(1);
                        femaleButton.setAlpha(0.5f);
                        male = true;
                        break;
                    case R.id.female:
                        femaleButton.setAlpha(1);
                        maleButton.setAlpha(0.5f);
                        male = false;
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();

        intent.putExtra(User.USER_NAME, userName.getText().toString());
        intent.putExtra(User.USER_GENDER, true); // hard-code value for testing

        setResult(RESULT_OK, intent);
        super.finish();
    }
}