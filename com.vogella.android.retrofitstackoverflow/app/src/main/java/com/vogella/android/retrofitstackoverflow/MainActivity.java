package com.vogella.android.retrofitstackoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private StackOverflowAPI stackoverflowAPI;
    private String token;
    private static final String key = "yourKey";

    private Button upvoteButton;
    private Button authenticateButton;

    private Spinner questionsSpinner;
    private Spinner answersSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionsSpinner = (Spinner) findViewById(R.id.questions_spinner);
        questionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Question question = (Question) parent.getAdapter().getItem(position);
                stackoverflowAPI.getAnswersForQuestion(question.getQuestionId()).enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        answersSpinner = (Spinner) findViewById(R.id.answers_spinner);

        authenticateButton = (Button) findViewById(R.id.authenticate_button);
        upvoteButton = (Button) findViewById(R.id.upvote_button);

        createStackoverflowAPI();
        stackoverflowAPI.getQuestions().enqueue(questionsCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(token != null){
            authenticateButton.setEnabled(false);
            upvoteButton.setEnabled(true);
        }
    }

    private void createStackoverflowAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StackOverflowAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        stackoverflowAPI = retrofit.create(StackOverflowAPI.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.authenticate_button:
                startActivityForResult(new Intent(this, WebViewActivity.class), 1);
                break;
            case R.id.upvote_button:
                Answer selectedAnswer = (Answer) answersSpinner.getSelectedItem();
                if (selectedAnswer != null) {
                    stackoverflowAPI.postUpvoteOnAnswer(selectedAnswer.getAnswerId(), token, key, "stackoverflow.com", false, "default").enqueue(upvoteCallback);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }

    Callback<QuestionsList> questionsCallback = new Callback<QuestionsList>() {
        @Override
        public void onResponse(Call<QuestionsList> call, Response<QuestionsList> response) {
            if (response.isSuccessful()) {
                QuestionsList questionList = response.body();
                ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<Question>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, questionList.getItems().toArray(new Question[questionList.getItems().size()]));
                questionsSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<QuestionsList> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<AnswersList> answersCallback = new Callback<AnswersList>() {
        @Override
        public void onResponse(Call<AnswersList> call, Response<AnswersList> response) {
            if (response.isSuccessful()) {
                AnswersList answersList = response.body();
                ArrayAdapter<Answer> arrayAdapter = new ArrayAdapter<Answer>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, answersList.getItems().toArray(new Answer[answersList.getItems().size()]));
                answersSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<AnswersList> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<ResponseBody> upvoteCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                Toast.makeText(MainActivity.this, "Upvote successful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "You already upvoted this answer", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            t.printStackTrace();
        }
    };
}