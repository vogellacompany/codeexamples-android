package de.vogella.android.ownservice.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyMessage implements Parcelable {

	private String text;
	private int value;

	public static final Parcelable.Creator<MyMessage> CREATOR = new Parcelable.Creator<MyMessage>() {

		@Override
		public MyMessage createFromParcel(Parcel source) {
			// Must read values in the same order as they were placed in
			String s = source.readString();
			int v = source.readInt();
			MyMessage myMessage = new MyMessage(s, v);
			return myMessage;
		}

		@Override
		public MyMessage[] newArray(int size) {
			return new MyMessage[size];
		}

	};

	public MyMessage(String text, int value) {
		this.value = value;
		this.text = text;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(text);
		parcel.writeInt(value);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MyMessage [text=" + text + ", value=" + value + "]";
	}

}
