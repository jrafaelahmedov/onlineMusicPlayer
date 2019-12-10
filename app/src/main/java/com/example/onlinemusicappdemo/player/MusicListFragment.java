package com.example.onlinemusicappdemo.player;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemusicappdemo.MainActivity;
import com.example.onlinemusicappdemo.R;
import com.example.onlinemusicappdemo.adapter.MusicItemAdapter;
import com.example.onlinemusicappdemo.allBundle.AllBundle;
import com.example.onlinemusicappdemo.constant.Constants;
import com.example.onlinemusicappdemo.lisener.ClickLisener;
import com.example.onlinemusicappdemo.pojo.AllData;
import com.example.onlinemusicappdemo.pojo.ResponseBody;
import com.example.onlinemusicappdemo.utility.CustomViewPagerAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicListFragment extends Fragment implements ClickLisener {

    private MainActivity mContext;
    private RecyclerView musicListRecyclerView;
    private TextView searchMusic, toolbarTitle;
    private MusicItemAdapter adapter;
    private List<AllData> allData = new ArrayList<>();
    private Toolbar toolbar;
    private AVLoadingIndicatorView progressbarMusicList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setupAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.music_list, container, false);

        musicListRecyclerView = layout.findViewById(R.id.musicListRecyclerView);
        searchMusic = layout.findViewById(R.id.searchMusic);
        toolbar = layout.findViewById(R.id.toolbarMusicPlay);
        toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        progressbarMusicList = layout.findViewById(R.id.progressbarMusicList);
        mContext.setSupportActionBar(toolbar);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupItems();
    }

    public static MusicListFragment getInstance() {
        return new MusicListFragment();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) mContext.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        EditText text = searchView.findViewById(R.id.search_src_text);
        text.setTextColor(Color.WHITE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(mContext.getComponentName()));
        }
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getMusicForName(s);
                progressbarMusicList.setVisibility(View.VISIBLE);

//                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void emptyHistory() {
        if (searchMusic != null) {
            searchMusic.setVisibility(View.VISIBLE);
        }
    }

    private void setupToolbar() {
        mContext.setupToolbar(toolbar, 1);
        toolbarTitle.setText("Player");
        toolbarTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }


    private void getMusicForName(String nameArtistOrMusic) {
        Call<ResponseBody> call = Constants.SEARCH_MUSIC.getSearch(nameArtistOrMusic);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (allData != null) {
                            allData.clear();
                            allData.addAll(response.body().getAllData());
                        }
                        adapter.notifyDataSetChanged();
                        progressbarMusicList.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                System.out.println("Rafael error " + t.getMessage());
            }
        });
    }


    private void setupAdapter() {
        adapter = new MusicItemAdapter(mContext, allData, this);
    }


    private void setupItems() {
        musicListRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        musicListRecyclerView.setHasFixedSize(true);
        musicListRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onPositionClicked(AllBundle allBundle, String type) {
        switch (type) {
            case "musicItem": {
                mContext.getPlayMusic(allBundle);
                break;
            }
        }
    }


    @Override
    public void onDetach() {
        mContext = null;
        super.onDetach();
    }
}
