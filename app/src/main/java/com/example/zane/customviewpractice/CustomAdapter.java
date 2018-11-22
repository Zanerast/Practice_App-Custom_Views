package com.example.zane.customviewpractice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

	String[] titles = {"Melancholic", "Phlegmatic", "Sanguine", "Choleric"};
	String[] subtitles = {
					"Earth, Black, Leonardo, Fred, Jerry, Paul",
					"Water, Green, Donatello, Velma, George, George",
					"Air, Red, Raphael, Daphne, Elaine, John",
					"Fire, Yellow, Michelangelo, Shaggy, Kramer, Ringo"};
	int[] images = {R.drawable.ic_bubble_chart_black_48dp,
					R.drawable.ic_ac_unit_black_48dp,
					R.drawable.ic_spa_black_48dp,
					R.drawable.ic_whatshot_black_48dp};

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).
						inflate(R.layout.list_item, viewGroup, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
		viewHolder.bind(i);
	}

	@Override
	public int getItemCount() {
		return 4;
	}


	class ViewHolder extends RecyclerView.ViewHolder {

		ImageView ivIcon;
		TextView tvTitle;
		TextView tvSubTitle;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			ivIcon = itemView.findViewById(R.id.icon);
			tvTitle = itemView.findViewById(R.id.title);
			tvSubTitle = itemView.findViewById(R.id.subtitle);
		}

		public void bind(int position) {
			ivIcon.setBackgroundResource(images[position]);
			tvTitle.setText(titles[position]);
			tvSubTitle.setText(subtitles[position]);
		}
	}
}
