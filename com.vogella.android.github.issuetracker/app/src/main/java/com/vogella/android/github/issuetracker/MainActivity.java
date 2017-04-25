package com.vogella.android.github.issuetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.observers.DisposableSingleObserver;

public class MainActivity extends AppCompatActivity implements CredentialsDialog.ICredentialsDialogListener {

    EditText ownerEditText;
    EditText repositoryEditText;
    RecyclerView list;
    ArrayList<Issue> issues;
    private String password = "";
    private String username = "";
    @Inject
    CommunicationController communicationController;
    DisposableSingleObserver<List<Issue>> issuesObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ownerEditText = (EditText) findViewById(R.id.owner_edittext);
        repositoryEditText = (EditText) findViewById(R.id.repository_edittext);
        if (BuildConfig.DEBUG) {
            ownerEditText.setText(BuildConfig.GITHUB_REPO_OWNER);
            repositoryEditText.setText(BuildConfig.GITHUB_REPO);
        }

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
        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        issues = new ArrayList<>();
        list.setAdapter(new IssueAdapter(issues));
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
            public void onSuccess(List<Issue> issues) {
                IssueAdapter listAdapter = (IssueAdapter) list.getAdapter();
                MainActivity.this.issues.clear();
                MainActivity.this.issues.addAll(new ArrayList<Issue>(issues));
                list.getAdapter().notifyDataSetChanged();
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
        if (BuildConfig.DEBUG && BuildConfig.GITHUB_USER.length() > 0) {
            username = BuildConfig.GITHUB_USER;
            password = BuildConfig.GITHUB_PW;
        }
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
        String owner = ownerEditText.getText().toString();
        String repository = repositoryEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please provide your credentials", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!username.isEmpty() && !password.isEmpty() && !owner.isEmpty() && !repository.isEmpty()) {
            issuesObserver = getIssuesObserver();
            communicationController.loadIssues(username, password, issuesObserver, owner, repository);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (issuesObserver != null && !issuesObserver.isDisposed()) {
            issuesObserver.dispose();
        }
    }
}
