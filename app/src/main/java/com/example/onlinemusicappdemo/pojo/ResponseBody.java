package com.example.onlinemusicappdemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResponseBody implements Parcelable {

    @SerializedName("data")
    @Expose
    List<AllData> allData = new ArrayList<>();

    protected ResponseBody(Parcel in) {
    }

    public static final Creator<ResponseBody> CREATOR = new Creator<ResponseBody>() {
        @Override
        public ResponseBody createFromParcel(Parcel in) {
            return new ResponseBody(in);
        }

        @Override
        public ResponseBody[] newArray(int size) {
            return new ResponseBody[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
