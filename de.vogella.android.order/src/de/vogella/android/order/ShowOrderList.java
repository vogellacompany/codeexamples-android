package de.vogella.android.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ShowOrderList extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button =(Button) findViewById(R.id.Button01);
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon));
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getBaseContext(), "Imagine here a coomunication with a service...", 5000).show();
			}
		});
    }
}