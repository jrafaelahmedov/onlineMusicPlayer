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
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

public class TopMiniIconsMusics extends RecyclerView.Adapter<TopMiniIconsMusics.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private ClickLisener listener;
    private AllBundle allBundle = new AllBundle();
    private List<AllData> allData;


    public TopMiniIconsMusics(Context context, List<AllData> allData, ClickLisener listener) {
        this.listener = listener;
        this.context = context;
        this.allData = allData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = inflater.inflate(R.layout.item_music_topbar, parent, false);
        return new TopMiniIconsMusics.Holder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            Picasso.get().load(allData.get(position).getAlbum().getCover_medium()).into(holder.albumImageItem);
    }

    @Override
    public int getItemCount() {
        if (allData != null) {
            return allData.size();
        }
        return 0;
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView albumImageItem;
        private WeakReference<ClickLisener> listenerRef = new WeakReference<>(listener);
        private ConstraintLayout item_music_topbar;

        public Holder(@NonNull View itemView) {
            super(itemView);
            item_music_topbar = itemView.findViewById(R.id.item_music_topbar);
            albumImageItem = itemView.findViewById(R.id.imageTop);
            item_music_topbar.setOnClickListener(OnSingleClickListener.wrap(this));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.music_item: {
                    allBundle.setPosition(getAdapterPosition());
                    allBundle.setAllData(allData);
                    listenerRef.get().onPositionClicked(allBundle, "musicItemTopIcon");
                    break;
                }
            }
        }
    }
}
