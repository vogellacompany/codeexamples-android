package com.vogella.android.mymathtrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CreateUserActivity extends Activity {

    RadioButton radioMale;
    RadioButton radioFemale;
    Button createUserButton;
    EditText edittextUserName;
    boolean gender = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        radioMale = findViewById(R.id.radio_male);
        radioFemale = findViewById(R.id.radio_female);
        createUserButton = findViewById(R.id.createUserButton);
        edittextUserName = findViewById(R.id.username);
        createUserButton.setEnabled(false);
    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_male:
                gender = false;
                radioFemale.setAlpha(0.2f);
                break;
            case R.id.radio_female:
                gender = true;
                radioMale.setAlpha(0.2f);
                break;
        }
        view.setAlpha(1);
        createUserButton.setEnabled(true);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelButton:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
            case R.id.createUserButton:
                createUser();
                setResult(Activity.RESULT_OK);
                break;
        }
    }

    private void createUser() {
        User user = new User(edittextUserName.getText().toString(), gender);
        String genderAsString = gender ? "female" : "male";
        Toast.makeText(getApplicationContext(), "User created with name: " + user.name + " and gender: " + genderAsString, Toast.LENGTH_LONG).show();
    }
}
