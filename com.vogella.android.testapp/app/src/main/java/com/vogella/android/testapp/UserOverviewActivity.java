package com.vogella.android.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserOverviewActivity extends AppCompatActivity {

    public static final int SUB_ACTIVITY_CREATE_USER = 10;
    public static final int SUB_ACTIVITY_LEARN = 20;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_overview);

        // TODO check persistence if user exists and load the existing one
        boolean userExists=false;
        // if no user found, create a new one
        if (!userExists){

            Intent intent = new Intent(this, CreateUserActivity.class);
            startActivityForResult(intent, SUB_ACTIVITY_CREATE_USER);
        }
    }




    // This is the callback for the started sub-activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SUB_ACTIVITY_CREATE_USER && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String name = extras.getString(User.USER_NAME);
                boolean gender = extras.getBoolean(User.USER_GENDER);
                user = new User(name, gender);
                updateUserInterface();
            }
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        intent.putExtra(User.USER_SKILL_POINTS, user.skillPoints);
        startActivityForResult(intent, SUB_ACTIVITY_LEARN);
    }

    private void updateUserInterface() {
        // TODO show the new user values in the UI
    }


}