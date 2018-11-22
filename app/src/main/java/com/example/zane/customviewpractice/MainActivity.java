package com.example.zane.customviewpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.btn_main_countdown)
	Button btnCountdown;
	@BindView(R.id.btn_custom_viewgroup)
	Button btnViewgroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		Timber.plant(new TimberTree());

		btnCountdown.setOnClickListener(v ->
						startActivity(new Intent(this, CountdownActivity.class)));

		btnViewgroup.setOnClickListener(v ->
						startActivity(new Intent(this, ViewGroupActivity.class)));
	}
}
