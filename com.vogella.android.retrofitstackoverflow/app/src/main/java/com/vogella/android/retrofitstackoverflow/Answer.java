package com.vogella.android.retrofitstackoverflow;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("answer_id")
    private int answerId;

    @SerializedName("is_accepted")
    private boolean accepted;

    private int score;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return answerId + " - Score: " + score + " - Accepted: " + (accepted ? "Yes" : "No");
    }
}
