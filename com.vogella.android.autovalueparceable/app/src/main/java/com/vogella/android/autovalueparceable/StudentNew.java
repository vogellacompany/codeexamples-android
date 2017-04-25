package com.vogella.android.autovalueparceable;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentNew implements Parcelable {
        private String id;
        private String name;
        private String grade;

        // Constructor
        public StudentNew(String id, String name, String grade){
            this.id = id;
            this.name = name;
            this.grade = grade;
       }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
       public StudentNew(Parcel in){
           String[] data = new String[3];

           in.readStringArray(data);
           this.id = data[0];
           this.name = data[1];
           this.grade = data[2];
       }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
       public void writeToParcel(Parcel dest, int flags) {
           dest.writeStringArray(new String[] {this.id,
                                               this.name,
                                               this.grade});
       }
       public static final Creator CREATOR = new Creator() {
           public StudentNew createFromParcel(Parcel in) {
               return new StudentNew(in);
           }

           public StudentNew[] newArray(int size) {
               return new StudentNew[size];
           }
       };

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
