package com.example.martial_arts_handbook;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Art implements Parcelable {
    public String name;
    public String country;
    public String description;
    public String url;
    public Art(String name, String country, String description, String url){
        this.name = name;
        this.country = country;
        this.description = description;
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        //parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(country);
        parcel.writeString(description);
        parcel.writeString(url);
    }

    public Art(Parcel in) {
        //id = in.readInt();
        name = in.readString();
        country = in.readString();
        description = in.readString();
        url = in.readString();
    }
    public static final Parcelable.Creator<Art> CREATOR = new Parcelable.Creator<Art>() {
        public Art createFromParcel(Parcel in) {
            return new Art(in);
        }
        public Art[] newArray(int size) {
            return new Art[size];
        }
    };
}
