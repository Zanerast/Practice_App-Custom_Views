package com.example.zane.customviewpractice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListItem extends ViewGroup {

	// Need to create references to the children found in the xml
	ImageView ivIcon;
	TextView tvTitle;
	TextView tvSubtitle;

	public CustomListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// Override Methods
	// If this fails, calls generateLayoutParams
	@Override
	protected boolean checkLayoutParams(LayoutParams p) {
		return p instanceof MarginLayoutParams;
	}

	// Gets called if child view has no params
	// Then assigns default(wrap_content) params to child view
	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	// If checkLayoutParams fails, this method is called
	// Will copy usable values from failed params into new instance
	// Margins are now enabled within our custom ViewGroup
	@Override
	protected LayoutParams generateLayoutParams(LayoutParams p) {
		return new MarginLayoutParams(p);
	}

	// Load params from xml
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	// findViewById cannot be called in the construction, as none of the children will be instantiated yet
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		ivIcon = findViewById(R.id.icon);
		tvTitle = findViewById(R.id.title);
		tvSubtitle = findViewById(R.id.subtitle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// measureChildWithMargins basically tells the child how big it can be, given margins and other views
		// Gives us the measureSpec values for the child
		measureChildWithMargins(ivIcon, widthMeasureSpec, 0, heightMeasureSpec, 0);
		MarginLayoutParams lp = (MarginLayoutParams) ivIcon.getLayoutParams();
		int iconWidthUsed = ivIcon.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
		int iconHeightUsed = ivIcon.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

		measureChildWithMargins(tvTitle, widthMeasureSpec, iconWidthUsed, heightMeasureSpec, 0);
		lp = (MarginLayoutParams) tvTitle.getLayoutParams();
		int titleWidthUsed = tvTitle.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
		int titleHeightUsed = tvTitle.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

		measureChildWithMargins(tvSubtitle, widthMeasureSpec, iconWidthUsed, heightMeasureSpec, titleHeightUsed);
		lp = (MarginLayoutParams) tvSubtitle.getLayoutParams();
		int subTitleWidthUsed = tvSubtitle.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
		int subTitleHeightUsed = tvSubtitle.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

		// Now calculate the size of the listview item
		int width = iconWidthUsed + Math.max(titleWidthUsed, subTitleWidthUsed)
						+ getPaddingLeft() + getPaddingRight();
		int height = Math.max(iconHeightUsed, titleHeightUsed + subTitleHeightUsed)
						+ getPaddingTop() + getPaddingBottom();

		// HAS TO BE CALLED, OTHERWISE EXCEPTION!!!
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final MarginLayoutParams iconLayoutParams = (MarginLayoutParams) ivIcon.getLayoutParams();
		int left = getPaddingLeft() + iconLayoutParams.leftMargin;
		int top = getPaddingTop() + iconLayoutParams.topMargin;
		int right = left + ivIcon.getMeasuredWidth();
		int bottom = top + ivIcon.getMeasuredHeight();

		ivIcon.layout(left, top, right, bottom);
		final int iconRightPlusMargin = right + iconLayoutParams.rightMargin;

		final MarginLayoutParams titleLayoutParams = (MarginLayoutParams) tvTitle.getLayoutParams();
		left = iconRightPlusMargin + titleLayoutParams.leftMargin;
		top = getPaddingTop() + titleLayoutParams.topMargin;
		right = left + tvTitle.getMeasuredWidth();
		bottom = top + tvTitle.getMeasuredHeight();

		tvTitle.layout(left, top, right, bottom);
		final int titleBottomPlusMargin = bottom + titleLayoutParams.bottomMargin;

		final MarginLayoutParams subtitleLayoutParams = (MarginLayoutParams) tvSubtitle.getLayoutParams();
		left = iconRightPlusMargin + subtitleLayoutParams.leftMargin;
		top = titleBottomPlusMargin + subtitleLayoutParams.topMargin;
		right = left + tvSubtitle.getMeasuredWidth();
		bottom = top + tvSubtitle.getMeasuredHeight();

		tvSubtitle.layout(left, top, right, bottom);


	}
}
