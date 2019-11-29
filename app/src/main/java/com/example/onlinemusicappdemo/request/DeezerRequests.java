package com.example.onlinemusicappdemo.request;

import com.example.onlinemusicappdemo.pojo.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DeezerRequests {

    @Headers({"X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com","X-RapidAPI-Key: e149f39f81msheddd8a6128a8251p19eea8jsn4e6139b2f534"})
    @GET("search")
    Call<ResponseBody> getSearch(@Query("q") String search);
}
