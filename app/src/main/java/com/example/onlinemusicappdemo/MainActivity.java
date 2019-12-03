package com.example.onlinemusicappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.onlinemusicappdemo.allBundle.AllBundle;
import com.example.onlinemusicappdemo.constant.Constants;
import com.example.onlinemusicappdemo.player.MusicListFragment;
import com.example.onlinemusicappdemo.player.PlayMusicFragment;
import com.example.onlinemusicappdemo.pojo.ResponseBody;
import com.example.onlinemusicappdemo.utility.Keys;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private int order = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
//        getMusicForName("eminem");
        getMusic(null);
    }


    public void getMusic(AllBundle bundle) {
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("allBundle", bundle);
        MusicListFragment musicListFragment = MusicListFragment.getInstance();
        musicListFragment.setArguments(bundle1);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragmentContainer, musicListFragment);
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        transaction.commit();
    }


    public void getPlayMusic(AllBundle allBundle) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("allBundle", allBundle);
        PlayMusicFragment paymentReceiptFragment = PlayMusicFragment.getInstance();
        paymentReceiptFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragmentContainer, paymentReceiptFragment);
        transaction.addToBackStack("");
        transaction.commit();
    }


    public void setupToolbar(Toolbar toolbar, int order) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.order = order;
        toolbar.setNavigationOnClickListener(view -> onBackButtonPressed());
    }

    public void onBackButtonPressed() {
        if (order == 0) {
            finish();
        } else if (order == 1) {
            fragmentManager.popBackStack();
        } else if (order == 2) {
            getMusic(null);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (order == 2) {
            getMusic(null);
        }
    }
}
