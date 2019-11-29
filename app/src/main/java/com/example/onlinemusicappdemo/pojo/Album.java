package com.example.onlinemusicappdemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Album implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("cover")
    @Expose
    private String cover;

    @SerializedName("cover_small")
    @Expose
    private String cover_small;

    @SerializedName("cover_medium")
    @Expose
    private String cover_medium;

    @SerializedName("cover_big")
    @Expose
    private String cover_big;

    @SerializedName("cover_xl")
    @Expose
    private String cover_xl;

    @SerializedName("tracklist")
    @Expose
    private String tracklist;

    @SerializedName("type")
    @Expose
    private String type;

    protected Album(Parcel in) {
        id = in.readLong();
        title = in.readString();
        cover = in.readString();
        cover_small = in.readString();
        cover_medium = in.readString();
        cover_big = in.readString();
        cover_xl = in.readString();
        tracklist = in.readString();
        type = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(cover);
        dest.writeString(cover_small);
        dest.writeString(cover_medium);
        dest.writeString(cover_big);
        dest.writeString(cover_xl);
        dest.writeString(tracklist);
        dest.writeString(type);
    }
}
