package testing.android.vogella.com.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("Running");
        myTask.execute("test");
    }

    final AsyncTask<String, Void, String> myTask = new AsyncTask<String, Void, String>() {

        @Override
        protected String doInBackground(String... arg0) {
            return "Long running stuff";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText("Done");
        }

    };

}