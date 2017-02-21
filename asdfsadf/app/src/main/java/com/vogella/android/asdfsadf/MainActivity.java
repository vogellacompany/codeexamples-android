package com.vogella.android.asdfsadf;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {

//        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        doSomethingVeryLong();
//        findViewById(R.id.progressBar).setVisibility(View.GONE);
        final Button button = (Button) findViewById(R.id.button);

        AsyncTask<Integer, Void, String> asyncTask = new
                AsyncTask<Integer, Void, String>() {
                    @Override
                    protected String doInBackground(Integer... params) {
                        int i = params[0];
                        doSomethingVeryLong();
                        String s = "";
                        for (int j = 0; j <= i; j++) {
                            s += "Android rocks ";
                        }
                        return s;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        button.setText("Finished download");
                    }
                };

        asyncTask.execute(2);
    }


    private void doSomethingVeryLong() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}


