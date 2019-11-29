package com.example.onlinemusicappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.onlinemusicappdemo.constant.Constants;
import com.example.onlinemusicappdemo.pojo.ResponseBody;
import com.example.onlinemusicappdemo.utility.Keys;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView_val_stick;
    private ConstraintLayout imageView_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMusicForName("eminem");

    }


    private void getMusicForName(String nameArtistOrMusic) {
        Call<ResponseBody> call = Constants.SEARCH_MUSIC.getSearch(nameArtistOrMusic);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        System.out.println("Rafael " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                System.out.println("Rafael error " + t.getMessage());
            }
        });
    }





}
