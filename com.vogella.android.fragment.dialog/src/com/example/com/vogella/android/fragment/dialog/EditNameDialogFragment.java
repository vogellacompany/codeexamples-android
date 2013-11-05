package com.example.com.vogella.android.fragment.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditNameDialogFragment extends DialogFragment {

	private EditText mEditText;

	public EditNameDialogFragment() {
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_editnamedialog,
				container);
		mEditText = (EditText) view.findViewById(R.id.txt_your_name);
		getDialog().setTitle("Please enter your name");

		return view;
	}
}