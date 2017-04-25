package com.vogella.android.autovalue;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;

@AutoValue public abstract class Task implements Parcelable{

    public abstract long id();
    public abstract String summary();
    public abstract String description();

    static Builder builder() {
        return new AutoValue_Task.Builder();
    }
    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setId(long value);
        abstract Builder setSummary(String value);
        abstract Builder setDescription(String value);
        abstract Task build();
    }

}