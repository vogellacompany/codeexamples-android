package com.vogella.android.retrofittwitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String credentials = Credentials.basic("aConsumerKey", "aSecret");

    Button requestTokenButton;
    Button requestUserDetailsButton;
    EditText usernameEditText;
    TextView usernameTextView;

    TextView nameTextView;
    TextView locationTextView;
    TextView urlTextView;
    TextView descriptionTextView;

    TwitterApi twitterApi;
    OAuthToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestTokenButton = (Button) findViewById(R.id.request_token_button);
        requestUserDetailsButton = (Button) findViewById(R.id.request_user_details_button);
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        usernameTextView = (TextView) findViewById(R.id.username_textview);

        nameTextView = (TextView) findViewById(R.id.name_textview);
        locationTextView = (TextView) findViewById(R.id.location_textview);
        urlTextView = (TextView) findViewById(R.id.url_textview);
        descriptionTextView = (TextView) findViewById(R.id.description_textview);

        createTwitterApi();
    }

    private void createTwitterApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        token != null ? token.getAuthorization() : credentials);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TwitterApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        twitterApi = retrofit.create(TwitterApi.class);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.request_token_button:
                twitterApi.postCredentials("client_credentials").enqueue(tokenCallback);
                break;
            case R.id.request_user_details_button:
                String editTextInput = usernameEditText.getText().toString();
                if (!editTextInput.isEmpty()) {
                    twitterApi.getUserDetails(editTextInput).enqueue(userDetailsCallback);
                } else {
                    Toast.makeText(this, "Please provide a username", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    Callback<OAuthToken> tokenCallback = new Callback<OAuthToken>() {
        @Override
        public void onResponse(Call<OAuthToken> call, Response<OAuthToken> response) {
            if (response.isSuccessful()) {
                requestTokenButton.setEnabled(false);
                requestUserDetailsButton.setEnabled(true);
                usernameTextView.setEnabled(true);
                usernameEditText.setEnabled(true);
                token = response.body();
            } else {
                Toast.makeText(MainActivity.this, "Failure while requesting token", Toast.LENGTH_LONG).show();
                Log.d("RequestTokenCallback", "Code: " + response.code() + "Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<OAuthToken> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<UserDetails> userDetailsCallback = new Callback<UserDetails>() {
        @Override
        public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
            if (response.isSuccessful()) {
                UserDetails userDetails = response.body();

                nameTextView.setText(userDetails.getName() == null ? "no value" : userDetails.getName());
                locationTextView.setText(userDetails.getLocation() == null ? "no value" : userDetails.getLocation());
                urlTextView.setText(userDetails.getUrl() == null ? "no value" : userDetails.getUrl());
                descriptionTextView.setText(userDetails.getDescription().isEmpty() ? "no value" : userDetails.getDescription());
            } else {
                Toast.makeText(MainActivity.this, "Failure while requesting user details", Toast.LENGTH_LONG).show();
                Log.d("UserDetailsCallback", "Code: " + response.code() + "Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<UserDetails> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
