package com.example.onlinemusicappdemo.constant;

import com.example.onlinemusicappdemo.request.DeezerRequests;
import com.example.onlinemusicappdemo.retrofit.RetrofitClient;
import com.example.onlinemusicappdemo.utility.Keys;

public class Constants {

    public static final DeezerRequests SEARCH_MUSIC = RetrofitClient.createService(DeezerRequests.class, Keys.URLS);
}
