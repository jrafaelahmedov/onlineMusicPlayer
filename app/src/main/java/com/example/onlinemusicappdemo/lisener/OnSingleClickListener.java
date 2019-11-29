package com.example.onlinemusicappdemo.lisener;

import android.view.View;

public abstract class OnSingleClickListener implements View.OnClickListener {

    private static final long MIN_DELAY = 700;
    private long lastClickTime;

    @Override
    public void onClick(View v) {
        long clickTime = lastClickTime;
        long now = System.currentTimeMillis();

        if ((now - clickTime) < MIN_DELAY) {
            return;
        }

        lastClickTime = now;
        onSingleClick(v);
    }

    public abstract void onSingleClick(View v);

    public static View.OnClickListener wrap(final View.OnClickListener onClickListener) {
        return new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                onClickListener.onClick(v);
            }
        };
    }
}
