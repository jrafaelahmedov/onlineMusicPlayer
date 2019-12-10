package com.example.onlinemusicappdemo.allBundle;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.onlinemusicappdemo.pojo.AllData;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AllBundle implements Parcelable {

    private List<AllData> allData = new ArrayList<>();
    private int position;


    public AllBundle() {

    }


    protected AllBundle(Parcel in) {
        allData = in.createTypedArrayList(AllData.CREATOR);
        position = in.readInt();
    }

    public static final Creator<AllBundle> CREATOR = new Creator<AllBundle>() {
        @Override
        public AllBundle createFromParcel(Parcel in) {
            return new AllBundle(in);
        }

        @Override
        public AllBundle[] newArray(int size) {
            return new AllBundle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(allData);
        dest.writeInt(position);
    }
}
