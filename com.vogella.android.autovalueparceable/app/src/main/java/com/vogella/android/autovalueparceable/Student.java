package com.vogella.android.autovalueparceable;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    private long id;
    private String name;
    private String grade;

    // Constructor
    public Student(long id, String name, String grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Parcelling part
       public Student(Parcel in){
           this.id = in.readLong();
           this.name = in.readString();
           this.grade =  in.readString();
       }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
       public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.grade);
       }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
