package com.vogella.android.retrofitstackoverflow;

import com.google.gson.annotations.SerializedName;

public class Question {

    private String title;
    private String link;

    @SerializedName("question_id")
    private String questionId;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return(title);
    }
}