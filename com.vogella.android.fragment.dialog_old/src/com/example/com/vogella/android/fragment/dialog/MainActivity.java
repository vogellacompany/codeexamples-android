package com.example.com.vogella.android.fragment.dialog;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		FragmentManager fm = getFragmentManager();
		switch (item.getItemId()) {

		case R.id.actionitem_alertdialog:
			AlertDialogFragment alertDialog = new AlertDialogFragment();
			alertDialog.setRetainInstance(true);
			alertDialog.show(fm, "alertfragment");
			break;
		case R.id.actionitem_userdialog:
			EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
			editNameDialog.setRetainInstance(true);
			editNameDialog.show(fm, "userfragemnt");
			break;

		default:
			break;
		}

		return true;
	}
}
