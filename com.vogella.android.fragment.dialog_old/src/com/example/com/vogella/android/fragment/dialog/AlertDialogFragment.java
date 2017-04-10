package com.example.com.vogella.android.fragment.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AlertDialogFragment extends DialogFragment {


	public AlertDialogFragment() {
		// Empty constructor required for DialogFragment
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder alertDialog = new AlertDialog.Builder(getActivity());
		alertDialog.setTitle("Alert!");
		alertDialog.setMessage("This is a notication dialog");
		return alertDialog.create();
	}
}