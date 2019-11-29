package com.example.onlinemusicappdemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Artist implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("picture_small")
    @Expose
    private String picture_small;

    @SerializedName("picture_medium")
    @Expose
    private String picture_medium;

    @SerializedName("picture_big")
    @Expose
    private String picture_big;

    @SerializedName("picture_xl")
    @Expose
    private String picture_xl;

    @SerializedName("tracklist")
    @Expose
    private String tracklist;

    @SerializedName("type")
    @Expose
    private String type;

    protected Artist(Parcel in) {
        id = in.readInt();
        name = in.readString();
        link = in.readString();
        picture = in.readString();
        picture_small = in.readString();
        picture_medium = in.readString();
        picture_big = in.readString();
        picture_xl = in.readString();
        tracklist = in.readString();
        type = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(link);
        dest.writeString(picture);
        dest.writeString(picture_small);
        dest.writeString(picture_medium);
        dest.writeString(picture_big);
        dest.writeString(picture_xl);
        dest.writeString(tracklist);
        dest.writeString(type);
    }
}
