package com.example.onlinemusicappdemo.player;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.onlinemusicappdemo.MainActivity;
import com.example.onlinemusicappdemo.R;
import com.example.onlinemusicappdemo.allBundle.AllBundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class PlayMusicFragment extends Fragment {

    private MainActivity mContext;
    private ImageView albumImage, retro_val_stick, shuffleImage, backWardIcon, forWardIcon, replayIcon;
    private FloatingActionButton playIcon;
    private TextView albumName, musicName;
    private ConstraintLayout val_Layout;
    private AllBundle allBundle = new AllBundle();
    private int position;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allBundle = getArguments().getParcelable("allBundle");
            if (allBundle != null) {
                position = allBundle.getPosition();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.play_music_fragment, container, false);
        val_Layout = layout.findViewById(R.id.valLayout);
        albumImage = layout.findViewById(R.id.albumImage);
        albumName = layout.findViewById(R.id.albumName);
        musicName = layout.findViewById(R.id.musicName);
        retro_val_stick = layout.findViewById(R.id.val_stick);
        shuffleImage = layout.findViewById(R.id.suffleMusic);
        backWardIcon = layout.findViewById(R.id.backWard);
        forWardIcon = layout.findViewById(R.id.forward);
        playIcon = layout.findViewById(R.id.btn_play_or_pause);
        replayIcon = layout.findViewById(R.id.replay);
        if (allBundle.getAllData().get(position).getPreview() != null && !allBundle.getAllData().get(position).getPreview().isEmpty()) {
            setImageValLayoutBackground();
        }
        return layout;
    }

    private void setImageValLayoutBackground() {
        Picasso.get().load(allBundle.getAllData().get(position).getArtist().getPicture_medium()).into(albumImage);
        albumName.setText(allBundle.getAllData().get(position).getTitle());
        musicName.setText(allBundle.getAllData().get(position).getTitle_short());


        rotateImage(retro_val_stick, 0, 20, 0.5f, 0f, 1000, 0);
        rotateLayout(val_Layout, 0, 360, 0.5f, 0.5f, allBundle.getAllData().get(position).getDuraction(), Animation.INFINITE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (MainActivity) context;
    }


    private void rotateImage(ImageView imageView, int fromDegree, int toDegree, float pivotX, float pivotY, int duraction, int repeatCount) {
        RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree,
                RotateAnimation.RELATIVE_TO_SELF, pivotX,
                RotateAnimation.RELATIVE_TO_SELF, pivotY);

        rotate.setRepeatCount(repeatCount);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setFillAfter(true);
        rotate.setDuration(duraction);
        rotate.setInterpolator(mContext, android.R.anim.linear_interpolator);
        imageView.startAnimation(rotate);

    }


    private void rotateLayout(ConstraintLayout imageView, int fromDegree, int toDegree, float pivotX, float pivotY, int duraction, int repeatCount) {
        RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree,
                RotateAnimation.RELATIVE_TO_SELF, pivotX,
                RotateAnimation.RELATIVE_TO_SELF, pivotY);

        rotate.setRepeatCount(repeatCount);
        rotate.setRepeatMode(Animation.RESTART);
        rotate.setFillAfter(true);
        rotate.setDuration(duraction);
        rotate.setInterpolator(mContext, android.R.anim.linear_interpolator);
        imageView.startAnimation(rotate);

    }

}
