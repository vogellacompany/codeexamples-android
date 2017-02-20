package com.vogella.android.retrofitgithubcomment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by david on 20.02.17.
 */

public class GithubIssue {

    String id;
    String title;
    String comments_url;

    @SerializedName("body")
    String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentsUrl() {
        return comments_url;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.comments_url = commentsUrl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return id +  " - " + title;
    }
}
