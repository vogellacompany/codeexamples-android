package com.vogella.android.retrofitgithub;

public class GithubRepo {
    String name;
    String owner;
    String url;

    @Override
    public String toString() {
        return(name + " " +  url);
    }
}
