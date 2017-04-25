package com.vogella.android.github.issuetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class MainActivity extends AppCompatActivity implements CredentialsDialog.ICredentialsDialogListener {

    EditText ownerEditText;
    EditText repositoryEditText;
    TextView issuesTextView;
    private String password = "";
    private String username = "";
    private CommunicationController communicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ownerEditText = (EditText) findViewById(R.id.owner_edittext);
        repositoryEditText = (EditText) findViewById(R.id.repository_edittext);
        repositoryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    queryForIssues();
                    return true;
                }
                return false;
            }
        });
        issuesTextView = (TextView) findViewById(R.id.issues_text);

        communicationController = new CommunicationController();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_credentials:
                showCredentialsDialog();
                return true;
            case R.id.menu_refresh:
                queryForIssues();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private DisposableSingleObserver<List<Issue>> getIssuesObserver() {
        return new DisposableSingleObserver<List<Issue>>() {
            @Override
            public void onSuccess(List<Issue> value) {
                for (Issue issues : value) {
                    issuesTextView.append(issues.toString() + "\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void showCredentialsDialog() {
        CredentialsDialog dialog = new CredentialsDialog();
        Bundle arguments = new Bundle();
        arguments.putString("username", username);
        arguments.putString("password", password);
        dialog.setArguments(arguments);

        dialog.show(getSupportFragmentManager(), "credentialsDialog");
    }

    @Override
    public void onDialogPositiveClick(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private void queryForIssues() {
        issuesTextView.setText("");
        String owner = ownerEditText.getText().toString();
        String repository = repositoryEditText.getText().toString();
        
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        if (owner.isEmpty() || repository.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please provider repository owner and/or name", Toast.LENGTH_SHORT).show();
            return;
        }

        communicationController.loadIssues(username, password, getIssuesObserver(), owner, repository);
    }
}
