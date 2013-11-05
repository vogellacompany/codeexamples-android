package com.vogella.android.stackview.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.StackView;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_main);
	 StackView stk = (StackView)this.findViewById(R.id.stackView1);
	 
	 ArrayList<StackItem> items = new ArrayList<StackItem>();
	 items.add(new StackItem("text1", this.getResources().getDrawable(R.drawable.ic_launcher)));
	 items.add(new StackItem("text2", this.getResources().getDrawable(R.drawable.ic_launcher)));
	 items.add(new StackItem("text3", this.getResources().getDrawable(R.drawable.ic_launcher)));
	 items.add(new StackItem("text4", this.getResources().getDrawable(R.drawable.ic_launcher)));
	 items.add(new StackItem("text5", this.getResources().getDrawable(R.drawable.ic_launcher)));
	 
	 StackAdapter adapt = new StackAdapter(this, R.layout.item, items);
	 
	 stk.setAdapter(adapt);
	 
	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
