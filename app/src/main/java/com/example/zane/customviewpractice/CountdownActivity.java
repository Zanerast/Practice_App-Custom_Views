package com.example.zane.customviewpractice;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountdownActivity extends AppCompatActivity {

    @BindView(R.id.view_countdown)
    CountdownView viewCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewCountdown.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        viewCountdown.stop();
    }
}
