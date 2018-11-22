package com.example.zane.customviewpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewGroupActivity extends AppCompatActivity {

    @BindView(R.id.rv_viewgroup)
    RecyclerView rvViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);

        ButterKnife.bind(this);

        recyclerView();
    }

    private void recyclerView() {
        RecyclerView.Adapter adapter = new CustomAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvViewGroup.setLayoutManager(layoutManager);
        rvViewGroup.setAdapter(adapter);
    }
}
