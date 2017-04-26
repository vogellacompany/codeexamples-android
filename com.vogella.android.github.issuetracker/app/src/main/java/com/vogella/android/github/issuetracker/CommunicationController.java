package com.vogella.android.github.issuetracker;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;

public class CommunicationController {

    @Inject
    GithubApi githubApi;

    @Inject
    public CommunicationController() {}

    public void loadIssues(String username, String password, DisposableSingleObserver<List<Issue>> observer, String owner, String repository) {
        githubApi.getIssues(Credentials.basic(username, password), owner, repository)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
