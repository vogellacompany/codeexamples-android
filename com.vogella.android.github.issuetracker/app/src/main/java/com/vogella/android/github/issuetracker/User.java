package com.vogella.android.github.issuetracker;

public class User {
    public int id;
    public String login;

    @Override
    public String toString() {
        return id + " - " + login;
    }
}
