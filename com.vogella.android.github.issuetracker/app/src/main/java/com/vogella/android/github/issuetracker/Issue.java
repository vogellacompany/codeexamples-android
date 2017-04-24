package com.vogella.android.github.issuetracker;

class Issue {
    public int id;
    public String title;

    @Override
    public String toString() {
        return id + " - " + title;
    }
}
