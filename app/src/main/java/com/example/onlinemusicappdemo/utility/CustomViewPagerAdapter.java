package com.example.onlinemusicappdemo.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.onlinemusicappdemo.R;
import com.example.onlinemusicappdemo.allBundle.AllBundle;
import com.example.onlinemusicappdemo.lisener.ClickLisener;
import com.example.onlinemusicappdemo.pojo.AllData;
import com.squareup.picasso.Picasso;
import com.tbuonomo.creativeviewpager.adapter.CreativePagerAdapter;

import java.util.List;

public class CustomViewPagerAdapter implements CreativePagerAdapter {

    private Context context;
    private List<AllData> allData;
    private int position;

    public CustomViewPagerAdapter(Context context, List<AllData> allData, int position) {
        this.context = context;
        this.allData = allData;
        this.position = position;
    }

    @Override
    public int getCount() {
        if (allData != null) {
            return allData.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public View instantiateContentItem(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, int i) {
        View layout = layoutInflater.inflate(R.layout.play_music_fragment, viewGroup, false);
        ImageView imageView = layout.findViewById(R.id.albumImage);
        TextView musicName = layout.findViewById(R.id.musicName);
        TextView albumName = layout.findViewById(R.id.albumName);
        Picasso.get().load(allData.get(position).getAlbum().getCover_medium()).into(imageView);
        albumName.setText(allData.get(position).getTitle());
        musicName.setText(allData.get(position).getArtist().getName());
        return layout;
    }

    @NonNull
    @Override
    public View instantiateHeaderItem(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, int i) {
        View layout = layoutInflater.inflate(R.layout.item_music_topbar, viewGroup, false);
        ImageView imageView = layout.findViewById(R.id.imageTop);
        Picasso.get().load(allData.get(position).getAlbum().getCover_medium()).into(imageView);
        return layout;
    }

    @Override
    public boolean isUpdatingBackgroundColor() {
        return true;
    }

    @Override
    public Bitmap requestBitmapAtPosition(int i) {
        return null;
    }
}
