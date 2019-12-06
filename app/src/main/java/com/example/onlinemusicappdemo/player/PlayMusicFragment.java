package com.example.onlinemusicappdemo.player;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemusicappdemo.MainActivity;
import com.example.onlinemusicappdemo.R;
import com.example.onlinemusicappdemo.adapter.TopMiniIconsMusics;
import com.example.onlinemusicappdemo.allBundle.AllBundle;
import com.example.onlinemusicappdemo.lisener.ClickLisener;
import com.example.onlinemusicappdemo.lisener.OnSingleClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


public class PlayMusicFragment extends Fragment implements View.OnClickListener, ClickLisener {

    private MainActivity mContext;
    private ImageView albumImage, retro_val_stick, shuffleImage, previousMusic, nextMusic, replayIcon;
    private FloatingActionButton playIcon, pauseIcon;
    private TextView albumName, musicName, toolbarTitle, textViewTime;
    private Toolbar toolbar;
    private ConstraintLayout val_Layout;
    private AllBundle allBundle = new AllBundle();
    private int position;
    private String AUDIO_PATH = "";
    private MediaPlayer mediaPlayer;
    private boolean isPLAYING;
    private ObjectAnimator anim;
    private RecyclerView recyclerViewTopIcons;
    private TopMiniIconsMusics adapter;
    private SeekBar seekBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            allBundle = getArguments().getParcelable("allBundle");
            if (allBundle != null) {
                position = allBundle.getPosition();
            }
        }
        setupAdapter();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static PlayMusicFragment getInstance() {
        return new PlayMusicFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.play_music_fragment, container, false);
        val_Layout = layout.findViewById(R.id.valLayout);
        albumImage = layout.findViewById(R.id.albumImage);
        seekBar = layout.findViewById(R.id.seekBar);
        textViewTime = layout.findViewById(R.id.timeTextView);
        albumName = layout.findViewById(R.id.albumName);
        recyclerViewTopIcons = layout.findViewById(R.id.recyclerViewTopIcons);
        toolbar = layout.findViewById(R.id.toolbarPlayMusic);
        toolbarTitle = toolbar.findViewById(R.id.toolbarTitle);
        musicName = layout.findViewById(R.id.musicName);
        retro_val_stick = layout.findViewById(R.id.val_stick);
        shuffleImage = layout.findViewById(R.id.suffleMusic);
        previousMusic = layout.findViewById(R.id.previousMusic);
        nextMusic = layout.findViewById(R.id.nextMusic);
        playIcon = layout.findViewById(R.id.btn_play);
        nextMusic.setOnClickListener(OnSingleClickListener.wrap(this));
        previousMusic.setOnClickListener(OnSingleClickListener.wrap(this));
        pauseIcon = layout.findViewById(R.id.btn_pause);
        pauseIcon.setOnClickListener(OnSingleClickListener.wrap(this));
        playIcon.setOnClickListener(OnSingleClickListener.wrap(this));
        replayIcon = layout.findViewById(R.id.replay);

        if (allBundle.getAllData().get(position).getPreview() != null && !allBundle.getAllData().get(position).getPreview().isEmpty()) {
            setImageValLayoutBackground(position);
        }
        return layout;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupItems();
    }

    private void setupToolbar() {
        mContext.setupToolbar(toolbar, 1);
        toolbarTitle.setText(allBundle.getAllData().get(position).getTitle());
        toolbarTitle.setVisibility(View.VISIBLE);
    }

    private void setupAdapter() {
        adapter = new TopMiniIconsMusics(mContext, allBundle.getAllData(), this);
    }

    private void setupItems() {
        recyclerViewTopIcons.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTopIcons.setHasFixedSize(true);
        recyclerViewTopIcons.setAdapter(adapter);
    }

    private void setImageValLayoutBackground(int position) {
        Picasso.get().load(allBundle.getAllData().get(position).getAlbum().getCover_medium()).into(albumImage);
        albumName.setText(allBundle.getAllData().get(position).getTitle());
        musicName.setText(allBundle.getAllData().get(position).getArtist().getName());
        AUDIO_PATH = allBundle.getAllData().get(position).getPreview();
        onRadioClick();
        rotateImage(retro_val_stick, 0, 30, 0.5f, 0f, 300, 0);
        rotateLayout(val_Layout, 0, 360, 0.5f, 0.5f, 2000, Animation.INFINITE);
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
        if (repeatCount == 0) {
            anim.pause();
            return;
        } else if (anim != null) {
            anim.resume();
            return;
        } else {
            anim = ObjectAnimator.ofFloat(imageView, "rotation", fromDegree, toDegree);
            anim.setDuration(duraction);
            anim.setRepeatCount(repeatCount);
            anim.setRepeatMode(ObjectAnimator.RESTART);
            anim.setInterpolator(new LinearInterpolator());
            anim.start();
        }
    }


    private void updateSeekBar() {

        try {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            textViewTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
            seekBar.postDelayed(runnable, 100);
        } catch (Exception e) {

        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }


    Runnable runnable = this::updateSeekBar;

    public void onRadioClick() {
        if (!isPLAYING) {
            if (mediaPlayer!=null){
                mediaPlayer.stop();
            }
            isPLAYING = true;
            playIcon.hide();
            pauseIcon.show();
            Uri soundUri = Uri.parse(AUDIO_PATH);
            try {

//                mediaPlayer = MediaPlayer.create(mContext, soundUri);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(mContext, soundUri);
                mediaPlayer.prepare();
                mediaPlayer.start();

                updateSeekBar();

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        /* show media player layout */
                        seekBar.setMax(mediaPlayer.getDuration());
                        updateSeekBar();
                    }
                });

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        pauseIcon.hide();
                        playIcon.show();
                        isPLAYING = false;
                        stopPlaying();
                    }
                });
                mediaPlayer.setOnCompletionListener(mp -> {
                    playIcon.show();
                    pauseIcon.hide();
                    rotateImage(retro_val_stick, 30, 0, 0.5f, 0f, 300, 0);
                    rotateLayout(val_Layout, 0, 0, 0.5f, 0.5f, 0, 0);
                });
            } catch (Exception e) {
                System.out.println("Rafael cach ");
            }
        } else {
            pauseIcon.hide();
            playIcon.show();
            isPLAYING = false;
            stopPlaying();
        }
    }

    private void stopPlaying() {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }


    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pause: {
                pauseIcon.hide();
                playIcon.show();
                mediaPlayer.pause();
                rotateImage(retro_val_stick, 30, 0, 0.5f, 0f, 300, 0);
                rotateLayout(val_Layout, 0, 0, 0.5f, 0.5f, 0, 0);
                break;
            }
            case R.id.btn_play: {
                playIcon.hide();
                pauseIcon.show();
                mediaPlayer.start();
                rotateImage(retro_val_stick, 0, 30, 0.5f, 0f, 300, 0);
                rotateLayout(val_Layout, 0, 360, 0.5f, 0.5f, 2000, Animation.INFINITE);
                break;
            }
            case R.id.nextMusic: {
                int currentPosition = position + 1;
                if (allBundle.getAllData().size() < currentPosition) {
                    return;
                } else {
                    position = currentPosition;
                    isPLAYING = false;
                    setImageValLayoutBackground(currentPosition);
                }
                break;
            }
            case R.id.previousMusic: {
                if (position==0){
                    return;
                }
                int currentPosition = position - 1;
                if (allBundle.getAllData().size() < currentPosition) {
                    return;
                } else {
                    position = currentPosition;
                    isPLAYING = false;
                    setImageValLayoutBackground(currentPosition);
                }
                break;
            }
        }
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
}
