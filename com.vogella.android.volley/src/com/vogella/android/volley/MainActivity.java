package com.vogella.android.volley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		final TextView mTextView = (TextView) findViewById(R.id.result);

		// Instantiate the RequestQueue.
		RequestQueue queue = Volley.newRequestQueue(this);
		String url = "http://www.vogella.com";

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET,
				url, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						mTextView.setText("Response is: "
								+ response.substring(0, 500));

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						mTextView.setText("That didn't work!");
					}
				});
		// Add the request to the RequestQueue.
		queue.add(stringRequest);
	}
}
