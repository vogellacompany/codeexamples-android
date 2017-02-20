package com.vogella.android.retrofitgithubcomment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface GithubAPI {
    String ENDPOINT = "https://api.github.com";

    @GET("/issues")
    Call<List<GithubIssue>> getIssues();

    @POST
    Call<ResponseBody> postComment(@Url String url, @Body GithubIssue issue);
}
