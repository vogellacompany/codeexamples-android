package com.vogella.android.github.issuetracker;

import java.util.List;

import dagger.Provides;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GithubApi {

    String BASE_URL = "https://api.github.com";

    @GET("/repos/{owner}/{repository}/issues")
    Single<List<Issue>> getIssues(@Header("Authorization") String credentials, @Path("owner") String owner, @Path("repository") String repository);
}
