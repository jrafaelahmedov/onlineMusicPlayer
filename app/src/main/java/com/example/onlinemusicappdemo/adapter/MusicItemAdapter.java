package com.example.onlinemusicappdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemusicappdemo.R;
import com.example.onlinemusicappdemo.allBundle.AllBundle;
import com.example.onlinemusicappdemo.lisener.ClickLisener;
import com.example.onlinemusicappdemo.lisener.OnSingleClickListener;
import com.example.onlinemusicappdemo.pojo.AllData;

import java.lang.ref.WeakReference;
import java.util.List;


public class MusicItemAdapter extends RecyclerView.Adapter<MusicItemAdapter.Holder>  {

    private Context context;
    private LayoutInflater inflater;
    private ClickLisener listener;
    private AllBundle allBundle = new AllBundle();
    private List<AllData> allData;

    public MusicItemAdapter(Context context, List<AllData> allData, ClickLisener listener) {
        this.listener = listener;
        this.context = context;
        this.allData = allData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.music_item, parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView albumNameItem, musicNameItem;
        private ImageView albumImageItem;
        private WeakReference<ClickLisener> listenerRef = new WeakReference<>(listener);
        private ConstraintLayout music_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            music_item = itemView.findViewById(R.id.music_item);
            albumImageItem = itemView.findViewById(R.id.albumImageItem);
            albumNameItem = itemView.findViewById(R.id.albumNameItem);
            musicNameItem = itemView.findViewById(R.id.musicNameItem);
            music_item.setOnClickListener(OnSingleClickListener.wrap(this));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.music_item: {
                    allBundle.setPosition(getAdapterPosition());
                    allBundle.setAllData(allData);
                    listenerRef.get().onPositionClicked(allBundle, "musicItem");
                    break;
                }
            }
        }
    }
}
