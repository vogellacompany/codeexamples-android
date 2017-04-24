package com.vogella.android.github.issuetracker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

public class CredentialsDialog extends DialogFragment {

    EditText usernameEditText;
    EditText passwordEditText;
    ICredentialsDialogListener listener;

    public interface ICredentialsDialogListener {
        void onDialogPositiveClick(String username, String password);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ICredentialsDialogListener) {
            listener = (ICredentialsDialogListener) getActivity();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_credentials, null);
        usernameEditText = (EditText) view.findViewById(R.id.username_edittext);
        passwordEditText = (EditText) view.findViewById(R.id.password_edittext);

        usernameEditText.setText(getArguments().getString("username"));
        passwordEditText.setText(getArguments().getString("password"));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Credentials")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Store", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onDialogPositiveClick(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                        }
                    }
                });
        return builder.create();
    }
}
