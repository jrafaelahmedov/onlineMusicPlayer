package com.example.onlinemusicappdemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class AllData implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("readable")
    @Expose
    private boolean readable;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("title_short")
    @Expose
    private String title_short;

    @SerializedName("title_version")
    @Expose
    private String title_version;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("duration")
    @Expose
    private int duraction;

    @SerializedName("rank")
    @Expose
    private int rank;

    @SerializedName("explicit_lyrics")
    @Expose
    private boolean explicit_lyrics;

    @SerializedName("explicit_content_lyrics")
    @Expose
    private int explicit_content_lyrics;

    @SerializedName("explicit_content_cover")
    @Expose
    private int explicit_content_cover;

    @SerializedName("preview")
    @Expose
    private String preview;

    @SerializedName("artist")
    @Expose
    private Artist artist;

    @SerializedName("album")
    @Expose
    private Album album;

    @SerializedName("type")
    @Expose
    private String type;

    protected AllData(Parcel in) {
        id = in.readLong();
        readable = in.readByte() != 0;
        title = in.readString();
        title_short = in.readString();
        title_version = in.readString();
        link = in.readString();
        duraction = in.readInt();
        rank = in.readInt();
        explicit_lyrics = in.readByte() != 0;
        explicit_content_lyrics = in.readInt();
        explicit_content_cover = in.readInt();
        preview = in.readString();
        artist = in.readParcelable(Artist.class.getClassLoader());
        type = in.readString();
    }

    public static final Creator<AllData> CREATOR = new Creator<AllData>() {
        @Override
        public AllData createFromParcel(Parcel in) {
            return new AllData(in);
        }

        @Override
        public AllData[] newArray(int size) {
            return new AllData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeByte((byte) (readable ? 1 : 0));
        dest.writeString(title);
        dest.writeString(title_short);
        dest.writeString(title_version);
        dest.writeString(link);
        dest.writeInt(duraction);
        dest.writeInt(rank);
        dest.writeByte((byte) (explicit_lyrics ? 1 : 0));
        dest.writeInt(explicit_content_lyrics);
        dest.writeInt(explicit_content_cover);
        dest.writeString(preview);
        dest.writeParcelable(artist, flags);
        dest.writeString(type);
    }
}
